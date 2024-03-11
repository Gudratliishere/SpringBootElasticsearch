package com.gudratli.elasticsearch.service.impl;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.repository.ProductRepository;
import com.gudratli.elasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductElasticsearchRepositoryService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public List<Product> findByDescription(String desc) {
        return productRepository.findFuzzyByDescription(desc);
    }
}
