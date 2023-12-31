package com.fabiankevin.orderserviceapp.application.web.mapper;

import com.fabiankevin.domainstarter.domain.Amount;
import com.fabiankevin.orderserviceapp.application.web.dto.request.PlaceOrderRequest;
import com.fabiankevin.orderserviceapp.core.domain.Item;
import com.fabiankevin.orderserviceapp.core.domain.Order;
import com.fabiankevin.orderserviceapp.core.domain.value.Currency;
import com.fabiankevin.orderserviceapp.core.domain.value.Note;
import com.fabiankevin.orderserviceapp.core.domain.value.Quantity;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.ItemCommand;
import com.fabiankevin.orderserviceapp.core.usecases.inbound.PlaceOrderCommand;
import com.fabiankevin.orderserviceapp.infrastructure.entities.ItemEntity;
import com.fabiankevin.orderserviceapp.infrastructure.entities.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DefaultOrderMapper implements OrderMapper {

    @Override
    public PlaceOrderCommand toCommand(PlaceOrderRequest request) {
        return new PlaceOrderCommand(
                request.getCustomerId(),
                request.getItems().stream().map(itemRequest -> new ItemCommand(
                        itemRequest.getQuantity(),
                        itemRequest.getCode(),
                        itemRequest.getName(),
                        itemRequest.getDescription(),
                        Amount.of(itemRequest.getUnitPrice().getCurrency(), itemRequest.getUnitPrice().getValue()),
                        itemRequest.getProductId()
                )).toList(),
                request.getNote(),
                request.getCurrency()
        );
    }


    public OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .customerId(order.getCustomerId().toString())
                .currency(order.getCurrency())
                .note(order.getNote())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount().getValue())
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .build();
        orderEntity.setItems(order.getItems().stream().map(item -> toItemEntity(item, orderEntity)).collect(Collectors.toSet()));
        return orderEntity;
    }

    @Override
    public Order toOrder(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .customerId(UUID.fromString(orderEntity.getCustomerId()))
                .note(new Note(orderEntity.getNote()))
                .status(orderEntity.getStatus())
                .currency(new Currency(orderEntity.getCurrency()))
                .updatedDate(orderEntity.getUpdatedDate())
                .createdDate(orderEntity.getCreatedDate())
                .items(orderEntity.getItems().stream().map(DefaultOrderMapper::toItem).toList())
                .build();
    }

    private static ItemEntity toItemEntity(Item item, OrderEntity orderEntity) {
        return ItemEntity.builder()
                .id(item.getId())
                .order(orderEntity)
                .code(item.getCode())
                .name(item.getName())
                .description(item.getDescription())
                .amount(item.getUnitPrice().getValue())
                .currency(item.getUnitPrice().getCurrency())
                .quality(item.getQuantity().getValue())
                .productId(item.getProductId().toString())
                .createdDate(item.getCreatedDate())
                .updatedDate(item.getUpdatedDate())
                .build();
    }

    private static Item toItem(ItemEntity itemEntity){
        return Item.builder()
                .id(itemEntity.getId())
                .quantity(new Quantity(itemEntity.getQuality()))
                .unitPrice(new Amount(itemEntity.getCurrency(), itemEntity.getAmount()))
                .name(itemEntity.getName())
                .code(itemEntity.getCode())
                .description(itemEntity.getDescription())
                .updatedDate(itemEntity.getUpdatedDate())
                .createdDate(itemEntity.getCreatedDate())
                .productId(UUID.fromString(itemEntity.getProductId()))
                .build();
    }
}
