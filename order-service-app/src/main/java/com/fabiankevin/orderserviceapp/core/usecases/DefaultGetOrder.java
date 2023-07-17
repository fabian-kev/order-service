package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.orderserviceapp.core.exceptions.OrderNotFoundException;
import com.fabiankevin.orderserviceapp.core.port.db.OrderRepository;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.ItemDto;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.OrderDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultGetOrder implements GetOrder {
    private final OrderRepository orderRepository;

    public DefaultGetOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto execute(UUID id) {
        return orderRepository.retrieveById(id)
                .map(order -> {
                    List<ItemDto> itemDtos = order.getItems().stream()
                            .map(item -> new ItemDto(
                                    item.getId(),
                                    item.getQuantity().getValue(),
                                    item.getName(),
                                    item.getCode(),
                                    item.getDescription(),
                                    item.getUnitPrice(),
                                    item.getProductId()
                            )).collect(Collectors.toList());
                    return new OrderDto(
                            order.getId(),
                            order.getCustomerId(),
                            itemDtos,
                            order.getNote(),
                            order.getCurrency(),
                            order.getStatus(),
                            order.getCreatedDate(),
                            order.getUpdatedDate()
                    );
                })
                .orElseThrow(OrderNotFoundException::new);
    }
}
