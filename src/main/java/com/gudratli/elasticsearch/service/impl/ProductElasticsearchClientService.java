package com.gudratli.elasticsearch.service.impl;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.query.ProductQuery;
import com.gudratli.elasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductElasticsearchClientService implements ProductService {
    private final ProductQuery productQuery;

    @Override
    public void save(Product product) {
        productQuery.save(product);
    }

    @Override
    public Product getById(String id) {
        return productQuery.getById(id);
    }

    @Override
    public void deleteById(String id) {
        productQuery.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productQuery.findAll();
    }

    @Override
    public List<Product> findByDescription(String desc) {
        return productQuery.findByDescription(desc);
    }
}
