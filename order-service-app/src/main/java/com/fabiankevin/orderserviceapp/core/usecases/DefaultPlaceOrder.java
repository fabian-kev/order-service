package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.orderserviceapp.core.EventEmitter;
import com.fabiankevin.orderserviceapp.core.domain.Item;
import com.fabiankevin.orderserviceapp.core.domain.Order;
import com.fabiankevin.orderserviceapp.core.domain.value.Currency;
import com.fabiankevin.orderserviceapp.core.domain.value.Note;
import com.fabiankevin.orderserviceapp.core.domain.value.Quantity;
import com.fabiankevin.orderserviceapp.core.exceptions.GuardException;
import com.fabiankevin.orderserviceapp.core.port.db.OrderRepository;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.PlaceOrderCommand;

import java.util.UUID;

public class DefaultPlaceOrder implements PlaceOrder {
    private final EventEmitter eventEmitter;
    private final OrderRepository orderRepository;

    public DefaultPlaceOrder(EventEmitter eventEmitter, OrderRepository orderRepository) {
        this.eventEmitter = eventEmitter;
        this.orderRepository = orderRepository;
    }

    @Override
    public UUID execute(PlaceOrderCommand command) {
        Order order = toDomain(command);
        Order savedOrder = orderRepository.save(order);
        eventEmitter.emit(savedOrder);
        return savedOrder.getId();
    }

    private static Order toDomain(PlaceOrderCommand command) {
        return Order.builder()
                .customerId(command.customerId())
                .currency(new Currency(command.currency()))
                .note(new Note(command.note()))
                .items(command.items().stream()
                        .map(itemRequest -> Item.builder()
                                .quantity(new Quantity(itemRequest.quantity()))
                                .code(itemRequest.code())
                                .description(itemRequest.description())
                                .name(itemRequest.name())
                                .productId(itemRequest.productId())
                                .unitPrice(itemRequest.unitPrice())
                                .build()).toList())
                .asPendingOrder()
                .build();
    }
}
