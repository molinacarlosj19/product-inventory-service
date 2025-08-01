package com.carlosmolina.productinventoryservice.controller;

import com.carlosmolina.productinventoryservice.dto.ProductDTO;
import com.carlosmolina.productinventoryservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {

    private static final ProductService productServiceMock = Mockito.mock(ProductService.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        ProductDTO mockProduct = new ProductDTO();
        mockProduct.setCode("P001");
        mockProduct.setName("Mock Product");
        mockProduct.setPrice(new BigDecimal("200.00"));

        when(productServiceMock.createProduct(any(ProductDTO.class))).thenReturn(mockProduct);
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO requestProduct = new ProductDTO();
        requestProduct.setCode("P001");
        requestProduct.setName("Mock Product");
        requestProduct.setPrice(new BigDecimal("200.00"));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestProduct)))
                .andExpect(status().isCreated());
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ProductService productService() {
            return productServiceMock;
        }
    }
}
