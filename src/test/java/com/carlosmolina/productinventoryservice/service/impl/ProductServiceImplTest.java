package com.carlosmolina.productinventoryservice.service.impl;

import com.carlosmolina.productinventoryservice.dto.ProductDTO;
import com.carlosmolina.productinventoryservice.mapper.ProductMapper;
import com.carlosmolina.productinventoryservice.model.Product;
import com.carlosmolina.productinventoryservice.repository.ProductRepository;
import com.carlosmolina.productinventoryservice.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId("1");
        product.setCode("P001");
        product.setName("Product 1");
        product.setQuantity(10);
        product.setPrice(new BigDecimal("99.99"));
        product.setExpirationDate(LocalDate.now().plusDays(30));

        productDTO = new ProductDTO();
        productDTO.setId("1");
        productDTO.setCode("P001");
        productDTO.setName("Product 1");
        productDTO.setQuantity(10);
        productDTO.setPrice(new BigDecimal("99.99"));
        productDTO.setExpirationDate(LocalDate.now().plusDays(30));
    }

    @Test
    void testCreateProduct() {
        when(productMapper.productDTOToProduct(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.productToProductDTO(product)).thenReturn(productDTO);
        ProductDTO result = productService.createProduct(productDTO);

        assertEquals(productDTO.getId(), result.getId());
        verify(productRepository, times(1)).save(product);

    }

    @Test
    void testGetProductById(){
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(productMapper.productToProductDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById("1");
        assertEquals(productDTO.getId(), result.getId());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.productsToProductDTOs(List.of(product))).thenReturn(List.of(productDTO));

        List<ProductDTO> result = productService.getAllProducts();

        assertFalse(result.isEmpty());
        verify(productRepository).findAll();
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.productToProductDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.updateProduct("1", productDTO);

        assertEquals(productDTO.getId(), result.getId());
        verify(productRepository).save(product);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct("1");
        verify(repository).deleteById("1");
    }
}
