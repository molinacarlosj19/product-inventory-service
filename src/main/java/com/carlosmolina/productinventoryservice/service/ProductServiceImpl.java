package com.carlosmolina.productinventoryservice.service;

import com.carlosmolina.productinventoryservice.dto.ProductDTO;
import com.carlosmolina.productinventoryservice.mapper.ProductMapper;
import com.carlosmolina.productinventoryservice.model.Product;
import com.carlosmolina.productinventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product productSaved = productRepository.save(productMapper.productDTOToProduct(productDTO));
        return productMapper.productToProductDTO(productSaved);
    }

    @Override
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Product not found") );
        return productMapper.productToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productMapper.productsToProductDTOs(productRepository.findAll());
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Product not found") );

        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setExpirationDate(productDTO.getExpirationDate());

        return productMapper.productToProductDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
