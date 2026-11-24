
package com.observability.order_service.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.observability.order_service.dto.CreateOrderRequest;
import com.observability.order_service.dto.OrderResponse;

@Service
public class OrderService {

    private final AtomicLong orderIdGenerator =
            new AtomicLong(1);

    public OrderResponse createOrder(CreateOrderRequest request) {

        Long orderId = orderIdGenerator.getAndIncrement();

        return new OrderResponse(
                orderId,
                request.productId(),
                request.quantity(),
                "CREATED"
        );
    }
}
