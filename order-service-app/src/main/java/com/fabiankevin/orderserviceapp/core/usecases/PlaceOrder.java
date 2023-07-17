package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.orderserviceapp.core.usecases.inbound.PlaceOrderCommand;

import java.util.UUID;

public interface PlaceOrder {
    UUID execute(PlaceOrderCommand command);
}
