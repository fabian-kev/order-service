package com.fabiankevin.orderserviceapp.core;

import com.fabiankevin.orderserviceapp.core.domain.Order;

public interface EventEmitter {
    void emit(Order order);
}
