package com.fabiankevin.orderserviceapp.application.web.mapper;

import com.fabiankevin.orderserviceapp.application.web.dto.request.PlaceOrderRequest;
import com.fabiankevin.orderserviceapp.application.web.dto.response.OrderResponse;
import com.fabiankevin.orderserviceapp.core.domain.Order;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.PlaceOrderCommand;
import com.fabiankevin.orderserviceapp.infrastructure.entities.OrderEntity;


public interface OrderMapper {
    PlaceOrderCommand toCommand(PlaceOrderRequest request);
    OrderEntity toEntity(Order order);
    Order toOrder(OrderEntity orderEntity);
    OrderResponse toOrderResponse(Order order);
}
