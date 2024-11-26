package com.spring_cloud.eureka.client.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    @GetMapping("/testConnection")
    public String test() {
        return "connect success";
    }

    @GetMapping("/checkQuantity")
    public boolean checkQuantity(@RequestBody List<Integer> productIds) {

    }
}
