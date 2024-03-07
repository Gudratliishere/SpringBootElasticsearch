package com.gudratli.elasticsearch.query;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.gudratli.elasticsearch.constant.Indices;
import com.gudratli.elasticsearch.document.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductQuery {
    private final ElasticsearchClient elasticsearchClient;

    public void save(Product product) {
        try {
            log.info("Add product: {}", product);
            IndexResponse indexResponse = elasticsearchClient.index(i -> i
                    .index(Indices.PRODUCT_INDEX)
                    .id(product.getId())
                    .document(product));
            log.info("Document is being adding. Result: {}", indexResponse.result().name());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public Product getById(String id) {
        try {
            GetResponse<Product> response = elasticsearchClient.get(g -> g
                    .index(Indices.PRODUCT_INDEX)
                    .id(id), Product.class);

            log.info("Get by id - {}, response is {}", id, response);
            return response.source();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public void deleteById(String id) {
        try {
            DeleteResponse deleteResponse = elasticsearchClient.delete(d -> d
                    .index(Indices.PRODUCT_INDEX)
                    .id(id));

            log.info("Delete product. Response: {}", deleteResponse.result());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public List<Product> findAll() {
        try {
            SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s
                    .index(Indices.PRODUCT_INDEX), Product.class);

            List<Hit<Product>> hits = searchResponse.hits().hits();
            log.info("Find All, response: {}", searchResponse.hits().hits());
            return hits.stream().map(Hit::source).toList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    public List<Product> findByDescription(String desc) {
        try {
            SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s
                    .index(Indices.PRODUCT_INDEX)
                    .query(q -> q.fuzzy((p -> p.field("description").queryName(desc)))), Product.class);

            List<Hit<Product>> hits = searchResponse.hits().hits();
            log.info("Find All, response: {}", searchResponse.hits().hits());
            return hits.stream().map(Hit::source).toList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}
