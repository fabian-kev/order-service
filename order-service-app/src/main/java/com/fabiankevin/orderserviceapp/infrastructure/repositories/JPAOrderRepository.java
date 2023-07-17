package com.fabiankevin.orderserviceapp.infrastructure.repositories;

import com.fabiankevin.orderserviceapp.application.web.mapper.OrderMapper;
import com.fabiankevin.orderserviceapp.core.domain.Order;
import com.fabiankevin.orderserviceapp.core.port.db.OrderRepository;
import com.fabiankevin.orderserviceapp.infrastructure.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JPAOrderRepository implements OrderRepository {
    private final SpringOrderRepository springOrderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        return orderMapper.toOrder(springOrderRepository.save(orderEntity));
    }

    @Override
    public Optional<Order> retrieveById(UUID id) {
        return springOrderRepository.findById(id)
                .map(orderMapper::toOrder);
    }
}
