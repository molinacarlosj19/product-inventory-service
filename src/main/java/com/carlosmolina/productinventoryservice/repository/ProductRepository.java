package com.carlosmolina.productinventoryservice.repository;

import com.carlosmolina.productinventoryservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(String code);
}
