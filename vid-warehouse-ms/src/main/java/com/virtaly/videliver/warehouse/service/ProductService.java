package com.virtaly.videliver.warehouse.service;

import com.virtaly.videliver.warehouse.dto.CustomerOrder;
import com.virtaly.videliver.warehouse.dto.InventoryEvent;
import com.virtaly.videliver.warehouse.dto.PaymentEvent;
import com.virtaly.videliver.warehouse.exception.NoProductsFoundInCityException;
import com.virtaly.videliver.warehouse.exception.NoWarehouseFoundInCityException;
import com.virtaly.videliver.warehouse.model.Product;
import com.virtaly.videliver.warehouse.model.ProductPk;
import com.virtaly.videliver.warehouse.model.Warehouse;
import com.virtaly.videliver.warehouse.repository.ProductRepository;
import com.virtaly.videliver.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final WarehouseService warehouseService;
    private final KafkaTemplate<String, InventoryEvent> kafkaInventoryTemplate;
    private final KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;

    public void addProduct(Product product) {
        Optional<Product> foundProductOptional = findByProductIdInWarehouse(product.getId(), product.getWarehouseId());
        if (foundProductOptional.isPresent()) {
            Product foundProduct = foundProductOptional.get();
            foundProduct.setCount(foundProduct.getCount() + product.getCount());
            productRepository.save(foundProduct);
        } else {
            productRepository.save(product);
        }
    }

    public void updateProductForOrder(CustomerOrder order) {
        try {
            String city = order.getAddress().getCity();
            List<Warehouse> warehousesInCity = warehouseService.getWarehousesInCity(city);
            if (CollectionUtils.isEmpty(warehousesInCity)) {
                throw new NoWarehouseFoundInCityException(city);
            }
            List<Long> warehouseIds = warehousesInCity.stream()
                    .map(Warehouse::getId)
                    .toList();
            List<Product> productsInCity = findByProductIdInWarehouses(order.getProductId(), warehouseIds, order.getProductCount());
            if (CollectionUtils.isEmpty(productsInCity)) {
                throw new NoProductsFoundInCityException(order.getProductId(), city);
            }
            Product product = productsInCity.stream().findAny().get();
            product.setCount(product.getCount() - order.getProductCount());
            productRepository.save(product);
            InventoryEvent event = InventoryEvent.builder()
                    .type("INVENTORY_UPDATED")
                    .order(order)
                    .build();
            kafkaInventoryTemplate.send("new-inventory", event);
        } catch (Exception e) {
            PaymentEvent pe = PaymentEvent.builder()
                    .order(order)
                    .type("PAYMENT_REVERSED")
                    .build();
            kafkaPaymentTemplate.send("reversed-payments", pe);
        }
    }

    public void reverseInventoryForOrder(CustomerOrder order) {
        String city = order.getAddress().getCity();
        List<Warehouse> warehousesInCity = warehouseService.getWarehousesInCity(city);
        if (CollectionUtils.isEmpty(warehousesInCity)) {
            throw new NoWarehouseFoundInCityException(city);
        }
        List<Long> warehouseIds = warehousesInCity.stream()
                .map(Warehouse::getId)
                .toList();
        List<Product> productsInCity = findByProductIdInWarehouses(order.getProductId(), warehouseIds, 0);
        if (CollectionUtils.isEmpty(productsInCity)) {
            throw new NoProductsFoundInCityException(order.getProductId(), city);
        }
        Product product = productsInCity.stream().findAny().get();
        product.setCount(product.getCount() + order.getProductCount());
        productRepository.save(product);
        PaymentEvent paymentEvent = PaymentEvent.builder()
                .order(order)
                .type("PAYMENT_REVERSED")
                .build();
        kafkaPaymentTemplate.send("reversed-payments", paymentEvent);
    }

    public Optional<Product> findByProductIdInWarehouse(long productId, long warehouseId) {
        return productRepository.findById(new ProductPk(productId, warehouseId));
    }

    public List<Product> findByProductIdInWarehouses(long productId, List<Long> warehouseIds, int minCount) {
        return productRepository.findAllByIdAndWarehouseIdInAndCountGreaterThanEqual(productId, warehouseIds, minCount);
    }
}
