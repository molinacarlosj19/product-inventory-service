package com.carlosmolina.productinventoryservice.controller;

import com.carlosmolina.productinventoryservice.ProductInventoryServiceApplication;
import com.carlosmolina.productinventoryservice.model.Product;
import com.carlosmolina.productinventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

@SpringBootTest(classes = ProductInventoryServiceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test") // Usar configuración de test
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setCode("P001");
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100));

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }
}
