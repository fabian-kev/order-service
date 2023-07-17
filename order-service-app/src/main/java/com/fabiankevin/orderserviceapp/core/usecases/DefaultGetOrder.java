package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.orderserviceapp.core.domain.Order;
import com.fabiankevin.orderserviceapp.core.exceptions.OrderNotFoundException;
import com.fabiankevin.orderserviceapp.core.port.db.OrderRepository;

import java.util.UUID;

public class DefaultGetOrder implements GetOrder {
    private final OrderRepository orderRepository;

    public DefaultGetOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(UUID id) {
        return orderRepository.retrieveById(id)
                .orElseThrow(OrderNotFoundException::new);
    }
}
