package com.fabiankevin.orderserviceapp.core.usecases.inbound;

import com.fabiankevin.orderserviceapp.core.domain.Item;
import com.fabiankevin.orderserviceapp.core.domain.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID customerId,
        List<ItemDto> items,
        String note,
        String currency,
        OrderStatus status,
        Instant createdDate,
        Instant updatedDate
) {
}
