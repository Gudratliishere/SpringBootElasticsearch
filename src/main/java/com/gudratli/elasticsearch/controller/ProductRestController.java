package com.gudratli.elasticsearch.controller;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.query.ProductQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/product")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductQuery productQuery;

    @PostMapping
    public void save(@RequestBody Product product) {
        productQuery.save(product);
    }
}
