package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.orderserviceapp.core.usecases.inbound.OrderDto;

import java.util.UUID;

public interface GetOrder {
    OrderDto execute(UUID id);
}
