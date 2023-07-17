package com.fabiankevin.orderserviceapp.application.web;

import com.fabiankevin.orderserviceapp.application.web.dto.AmountDto;
import com.fabiankevin.orderserviceapp.application.web.dto.response.OrderIdResponse;
import com.fabiankevin.orderserviceapp.application.web.dto.request.PlaceOrderRequest;
import com.fabiankevin.orderserviceapp.application.web.dto.response.OrderResponse;
import com.fabiankevin.orderserviceapp.application.web.mapper.OrderMapper;
import com.fabiankevin.orderserviceapp.core.usecases.GetOrder;
import com.fabiankevin.orderserviceapp.core.usecases.PlaceOrder;
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
        return orderMapper.toOrderResponse(getOrder.execute(orderId));
    }
}
