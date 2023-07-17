package com.fabiankevin.orderserviceapp.application.web;

import com.fabiankevin.orderserviceapp.application.web.dto.AmountDto;
import com.fabiankevin.orderserviceapp.application.web.dto.response.OrderIdResponse;
import com.fabiankevin.orderserviceapp.application.web.dto.request.PlaceOrderRequest;
import com.fabiankevin.orderserviceapp.application.web.dto.response.OrderResponse;
import com.fabiankevin.orderserviceapp.application.web.mapper.OrderMapper;
import com.fabiankevin.orderserviceapp.core.usecases.GetOrder;
import com.fabiankevin.orderserviceapp.core.usecases.PlaceOrder;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.ItemDto;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final PlaceOrder placeOrder;
    private final GetOrder getOrder;
    private final OrderMapper orderMapper;


    @PostMapping
    public OrderIdResponse placeOrder(@Valid @RequestBody PlaceOrderRequest request){
        log.info("Request body = {}", request);
        return new OrderIdResponse(placeOrder.execute(orderMapper.toCommand(request)));
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable UUID orderId) {
        OrderDto orderDto = getOrder.execute(orderId);
        return OrderResponse.builder()
                .id(orderDto.id())
                .items(orderDto.items().stream().map(OrderController::toItemResponse).collect(Collectors.toList()))
                .currency(orderDto.currency())
                .status(orderDto.status())
                .customerId(orderDto.customerId())
                .note(orderDto.note())
                .updatedDate(orderDto.updatedDate())
                .createdDate(orderDto.createdDate())
                .build();
    }

    private static OrderResponse.Item toItemResponse(ItemDto itemDto){
        return OrderResponse.Item.builder()
                .id(itemDto.id())
                .code(itemDto.code())
                .name(itemDto.name())
                .description(itemDto.description())
                .code(itemDto.code())
                .productId(itemDto.productId())
                .unitPrice(new AmountDto(itemDto.unitPrice().getCurrency(), itemDto.unitPrice().getValue()))
                .quantity(itemDto.quantity())
                .build();
    }
}
