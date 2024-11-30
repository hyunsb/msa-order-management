package com.sparta.msa_exam.order.bootstrap.rest.config;

import com.sparta.msa_exam.order.bootstrap.rest.util.actorId.ActorIdArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    public static final String HEADER_ACTOR_ID = "X-User-Id";

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ActorIdArgumentResolver());
    }
}
