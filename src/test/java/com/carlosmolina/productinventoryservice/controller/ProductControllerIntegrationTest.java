package com.carlosmolina.productinventoryservice.controller;

import com.carlosmolina.productinventoryservice.model.Product;
import com.carlosmolina.productinventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Usar Mongo embebido
class ProductControllerIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void testSaveAndFindProducts() {
        Product product = new Product();
        product.setCode("P001");
        product.setName("Test Product");
        product.setPrice(new BigDecimal("100.00"));

        productRepository.save(product);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Test Product");
    }
}
