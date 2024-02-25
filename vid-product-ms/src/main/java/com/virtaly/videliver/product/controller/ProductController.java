package com.virtaly.videliver.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.product.dto.ProductEvent;
import com.virtaly.videliver.product.dto.UpdateProductInfoRequest;
import com.virtaly.videliver.product.model.Product;
import com.virtaly.videliver.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/retailer/{retailerId}")
    public List<Product> getRetailerProducts(@PathVariable long retailerId) {
        return productService.getRetailerProducts(retailerId);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PatchMapping("/{id}")
    public Product updateProductById(@PathVariable long id, @RequestBody UpdateProductInfoRequest updateInfoRequest) {
        return productService.updateProductInfo(id, updateInfoRequest);
    }

    @KafkaListener(topics = "new-products", groupId = "products-group")
    public void updateTotalCount(String productEvent) throws JsonProcessingException {
        log.info("Received event" + productEvent);
        ProductEvent p = new ObjectMapper().readValue(productEvent, ProductEvent.class);
        productService.updateProduct(p.getProduct());
    }
}
