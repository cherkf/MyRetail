package com.redsky.myretail;

import com.redsky.myretail.models.Products;
import com.redsky.myretail.repositories.ProductsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    
    @Autowired
    private ProductsRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Products> getAllProducts() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Products getProductById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyProductById(@PathVariable("id") ObjectId id, @Valid @RequestBody Products Products) {
        Products.set_id(id);
        repository.save(Products);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Products createProduct(@Valid @RequestBody Products Products) {
        Products.set_id(ObjectId.get());
        repository.save(Products);
        return Products;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}

