package com.sparta.msa_exam.gateway.config;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClients(defaultConfiguration = CustomRoadBalancerConfig.class)
public class LoadBalancerConfig {

}
