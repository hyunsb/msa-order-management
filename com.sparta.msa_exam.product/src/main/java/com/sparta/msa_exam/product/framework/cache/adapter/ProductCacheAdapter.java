package com.sparta.msa_exam.product.framework.cache.adapter;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.framework.cache.dto.ProductCache;
import com.sparta.msa_exam.product.framework.cache.repository.ProductCacheRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductCacheAdapter {

    private final ProductCacheRepository productCacheRepository;

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productCacheRepository.findAll().forEach(productCache ->
            products.add(productCache.toDomain()));

        return products;
    }

    public void deleteAll() {
        productCacheRepository.deleteAll();
    }

    public void saveAll(List<Product> products) {
        List<ProductCache> productCaches = products.stream().map(ProductCache::from).toList();
        productCacheRepository.saveAll(productCaches);
    }
}
