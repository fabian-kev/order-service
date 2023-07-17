package com.fabiankevin.orderserviceapp.infrastructure.events;

import com.fabiankevin.orderserviceapp.core.EventEmitter;
import com.fabiankevin.orderserviceapp.core.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultEventEmitter implements EventEmitter {
    @Override
    public void emit(Order order) {
        log.info("Emitting event={}", order);
    }
}
