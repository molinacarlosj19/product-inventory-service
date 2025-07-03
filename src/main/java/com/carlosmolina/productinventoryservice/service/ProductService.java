package com.carlosmolina.productinventoryservice.service;

import com.carlosmolina.productinventoryservice.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductById(String id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(String id, ProductDTO productDTO);
    void deleteProduct(String id);

}
