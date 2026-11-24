package com.observability.order_service.dto;

public record OrderResponse(
        Long orderId,
        Long productId,
        Integer quantity,
        String status
) {
}
