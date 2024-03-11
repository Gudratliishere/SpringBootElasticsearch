package com.gudratli.elasticsearch.repository.custom;

import com.gudratli.elasticsearch.document.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findFuzzyByDescription (String desc);
}
