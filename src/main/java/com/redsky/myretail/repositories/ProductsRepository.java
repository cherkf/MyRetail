package com.redsky.myretail.repositories;

import com.redsky.myretail.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Products, String> {
    Products findById(Long id);
}
