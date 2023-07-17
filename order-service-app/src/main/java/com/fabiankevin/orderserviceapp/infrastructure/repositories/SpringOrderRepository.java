package com.fabiankevin.orderserviceapp.infrastructure.repositories;

import com.fabiankevin.orderserviceapp.infrastructure.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringOrderRepository extends CrudRepository<OrderEntity, UUID> {
}
