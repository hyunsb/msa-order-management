package com.spring_cloud.eureka.client.product;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findByIds(List<Long> productIds) {
        return productRepository.findByIdIn(productIds);
    }

//    public  checkQuantity(List<Long> productIds) {
//
//        List<Product> products = productRepository.findByIdIn(productIds);
//        pr

//        List<Product> products = productRepository.findByIdIn(productIds);
//        if (products.isEmpty() || !Objects.equals(productIds.size(), products.size())) {
//            return false;
//        }
//        for (Product product : products) {
//            if (product.getQuantity() < 1) {
//                return false;
//            }
//        }
//        return true;
//    }
}
