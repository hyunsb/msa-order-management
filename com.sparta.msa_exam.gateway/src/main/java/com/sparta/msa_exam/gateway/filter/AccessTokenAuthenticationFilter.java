package com.sparta.msa_exam.gateway.filter;

import com.sparta.msa_exam.gateway.util.tokenprovider.AccessTokenProvider;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccessTokenAuthenticationFilter implements GlobalFilter {

    private static final List<String> publicPaths = List.of("/auth/signIn", "/auth/signUp");
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_USER_ID = "X-User-Id";

    private final AccessTokenProvider tokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isPublicUri(path)) {
            return chain.filter(exchange);
        }
        return checkAccessToken(exchange, chain);
    }

    private boolean isPublicUri(String requestPath) {
        for (String publicPath : publicPaths) {
            if (Objects.equals(publicPath, requestPath)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> checkAccessToken(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HEADER_AUTHORIZATION);
        Long actorId = tokenProvider.readToken(authHeader);

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
            .header(HEADER_USER_ID, String.valueOf(actorId))
            .build();

        ServerWebExchange mutatedExchange = exchange.mutate()
            .request(mutatedRequest)
            .build();

        return chain.filter(mutatedExchange);
    }
}
