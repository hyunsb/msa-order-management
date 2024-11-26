package com.spring_cloud.eureka.client.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIdIn(List<Long> ids);
}
