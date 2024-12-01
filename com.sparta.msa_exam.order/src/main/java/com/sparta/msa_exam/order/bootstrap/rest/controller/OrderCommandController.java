package com.sparta.msa_exam.order.bootstrap.rest.controller;

import com.sparta.msa_exam.order.application.domain.Order;
import com.sparta.msa_exam.order.application.domain.OrderForCreate;
import com.sparta.msa_exam.order.application.domain.Product;
import com.sparta.msa_exam.order.application.service.OrderService;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderApiResponse;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderApiResponse.Success;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderCreation;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderCreation.Request;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderCreation.Response;
import com.sparta.msa_exam.order.bootstrap.rest.dto.OrderDetailResponse;
import com.sparta.msa_exam.order.bootstrap.rest.util.actorId.ActorId;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderCommandController {

    private final OrderService orderService;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Value("${server.port}")
    private String port;

    @CircuitBreaker(name = "createOrder", fallbackMethod = "fallbackCreateOrder")
    @PostMapping
    public ResponseEntity<Success<OrderCreation.Response>> createOrder(
        @RequestParam(name = "fail", required = false, defaultValue = "false") boolean fail,
        @RequestBody List<Request> createOrderRequest,
        @ActorId Long actorId
    ) {
        checkCircuitBreakerExecute(fail);

        List<Product> orderedProducts = createOrderRequest.stream().map(Request::toDomain).toList();
        OrderForCreate orderForCreate = new OrderForCreate(orderedProducts, actorId);

        Order order = orderService.save(orderForCreate);
        OrderCreation.Response response = Response.from(order);

        return OrderApiResponse.success(response, HttpStatus.CREATED, port);
    }


    @CircuitBreaker(name = "checkCircuitBreakerExecute", fallbackMethod = "fallbackCheckCircuitBreakerExecute")
    public void checkCircuitBreakerExecute(boolean fail) {
        if (fail) {
            throw new IllegalArgumentException("CircuitBreaker execute");
        }
    }

    public ResponseEntity<Success<Long>> fallbackCreateOrder(
        boolean fail, List<Request> createOrderRequest, Long actorId, Throwable t
    ) {
        log.error("", t);
        throw new IllegalArgumentException("잠시 후에 주문 추가를 요청 해주세요. 사유=" + t.getMessage());
    }

    @PostConstruct
    public void registerEventListener() {
        circuitBreakerRegistry.circuitBreaker("createOrder").getEventPublisher()
            .onStateTransition(event -> log.info("#######CircuitBreaker State Transition: {}", event)) // 상태 전환 이벤트 리스너
            .onFailureRateExceeded(event -> log.info("#######CircuitBreaker Failure Rate Exceeded: {}", event)) // 실패율 초과 이벤트 리스너
            .onCallNotPermitted(event -> log.info("#######CircuitBreaker Call Not Permitted: {}", event)) // 호출 차단 이벤트 리스너
            .onError(event -> log.info("#######CircuitBreaker Error: {}", event)); // 오류 발생 이벤트 리스너
    }
}
