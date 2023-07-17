package com.fabiankevin.orderserviceapp.core.usecases;

import com.fabiankevin.orderserviceapp.core.domain.Order;

import java.util.UUID;

public interface GetOrder {
    Order execute(UUID id);
}
