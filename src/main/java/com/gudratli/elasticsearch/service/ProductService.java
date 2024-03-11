package com.gudratli.elasticsearch.service;

import com.gudratli.elasticsearch.document.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);

    Product getById(String id);

    void deleteById(String id);

    List<Product> findAll();

    List<Product> findByDescription(String desc);
}
