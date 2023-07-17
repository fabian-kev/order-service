package com.fabiankevin.orderserviceapp.core.usecases.inbound;

import java.util.List;
import java.util.UUID;

public record PlaceOrderCommand(
        UUID customerId,
        List<ItemCommand> items,
        String note,
        String currency
) {
}
