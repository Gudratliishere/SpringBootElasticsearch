package com.gudratli.elasticsearch.service.impl;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductElasticsearchOperationService implements ProductService {
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public void save(Product product) {
        elasticsearchOperations.save(product);
    }

    @Override
    public Product getById(String id) {
        return elasticsearchOperations.get(id, Product.class);
    }

    @Override
    public void deleteById(String id) {
        elasticsearchOperations.delete(id, Product.class);
    }

    @Override
    public List<Product> findAll() {
        Query query = NativeQuery.builder().build();
        SearchHits<Product> searchHit = elasticsearchOperations.search(query, Product.class);
        List<SearchHit<Product>> searchHits = searchHit.getSearchHits();

        return searchHits.stream().map(SearchHit::getContent).toList();
    }

    @Override
    public List<Product> findByDescription(String desc) {
        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .fuzzy(builder -> builder
                                .field("description")
                                .value(desc)))
                .build();
        SearchHits<Product> searchHit = elasticsearchOperations.search(query, Product.class);
        List<SearchHit<Product>> searchHits = searchHit.getSearchHits();

        return searchHits.stream().map(SearchHit::getContent).toList();
    }
}
