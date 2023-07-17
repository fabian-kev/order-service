package com.fabiankevin.orderserviceapp.core.port.db;

import com.fabiankevin.orderserviceapp.core.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> retrieveById(UUID id);
}
