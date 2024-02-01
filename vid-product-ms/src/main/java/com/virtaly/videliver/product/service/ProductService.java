package com.virtaly.videliver.product.service;

import com.virtaly.videliver.product.dto.UpdateProductInfoRequest;
import com.virtaly.videliver.product.dto.WarehouseProduct;
import com.virtaly.videliver.product.exception.ProductNotFoundException;
import com.virtaly.videliver.product.model.Product;
import com.virtaly.videliver.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product updateProductInfo(long productId, UpdateProductInfoRequest updateRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Optional.ofNullable(updateRequest.getName()).ifPresent(product::setName);
        Optional.ofNullable(updateRequest.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(updateRequest.getPrice()).ifPresent(product::setPrice);
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void updateProductCount(WarehouseProduct product) {
        Optional<Product> foundProductOptional = productRepository.findById(product.getProductId());
        if (foundProductOptional.isPresent()) {
            Product foundProduct = foundProductOptional.get();
            foundProduct.setTotalCount(foundProduct.getTotalCount() + product.getCount());
            productRepository.save(foundProduct);
        } else {
            Product newProduct = new Product();
            newProduct.setId(product.getProductId());
            newProduct.setRetailerId(product.getRetailerId());
            newProduct.setTotalCount(product.getCount());
        }

    }

    public List<Product> getRetailerProducts(long retailerId) {
        return productRepository.findAllByRetailerId(retailerId);
    }
}
