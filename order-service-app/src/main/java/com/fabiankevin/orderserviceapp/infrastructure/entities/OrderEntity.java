package com.fabiankevin.orderserviceapp.infrastructure.entities;


import com.fabiankevin.orderserviceapp.core.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String customerId;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order")
    private Set<ItemEntity> items;
    private String currency;
    private BigDecimal totalAmount;
    private String note;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Instant createdDate;
    private Instant updatedDate;


}