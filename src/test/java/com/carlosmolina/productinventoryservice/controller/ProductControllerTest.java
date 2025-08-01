package com.carlosmolina.productinventoryservice.controller;

import com.carlosmolina.productinventoryservice.dto.ProductDTO;
import com.carlosmolina.productinventoryservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    ProductControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        ProductDTO productDto = new ProductDTO();
        productDto.setCode("P001");
        productDto.setName("Mock Product");
        productDto.setPrice(new BigDecimal("200.00"));

        when(productServiceMock.createProduct(any(ProductDTO.class))).thenReturn(productDto);
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO productDto = new ProductDTO();
        productDto.setCode("P001");
        productDto.setName("Mock Product");
        productDto.setPrice(new BigDecimal("200.00"));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
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
