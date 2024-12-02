package com.sparta.msa_exam.order.framework.config;

import feign.RetryableException;
import feign.Retryer;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        return new CustomFeignRetryer();
    }

    @Slf4j
    private static class CustomFeignRetryer extends Retryer.Default {

        public CustomFeignRetryer() {
            super(TimeUnit.SECONDS.toSeconds(30), TimeUnit.SECONDS.toSeconds(5), 3);
        }

        @Override
        public void continueOrPropagate(RetryableException e) {
            log.warn("Retrying Feign request due to: {}", e.getMessage());
            super.continueOrPropagate(e);
        }

        @Override
        public Retryer clone() {
            return new CustomFeignRetryer();
        }
    }
}
