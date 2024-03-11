package com.gudratli.elasticsearch.repository;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.repository.custom.ProductRepositoryCustom;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String>, ProductRepositoryCustom {
}
