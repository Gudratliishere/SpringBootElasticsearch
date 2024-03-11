package com.gudratli.elasticsearch.repository.custom.impl;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.repository.custom.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Product> findFuzzyByDescription(String desc) {
        SearchHits<Product> searchHits = elasticsearchTemplate.search(NativeQuery.builder()
                .withQuery(q -> q
                        .fuzzy(builder -> builder
                                .field("description")
                                .value(desc)))
                .build(), Product.class);
        List<SearchHit<Product>> searchHitList = searchHits.getSearchHits();
        return searchHitList.stream().map(SearchHit::getContent).toList();
    }
}
