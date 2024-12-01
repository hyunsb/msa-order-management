package com.sparta.msa_exam.gateway.config;

import com.sparta.msa_exam.gateway.loadbalancer.WeightServiceInstanceSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoadBalancerConfig {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new WeightServiceInstanceSupplier();
    }
}
