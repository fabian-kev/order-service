package com.fabiankevin.orderserviceapp.application.web.dto.response;

import com.fabiankevin.orderserviceapp.application.web.dto.ItemDto;
import com.fabiankevin.orderserviceapp.core.domain.OrderStatus;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class OrderResponse {

    private UUID id;
    private UUID customerId;
    private List<ItemDto> items;
    private String note;
    private String currency;
    private OrderStatus status;
    private Instant createdDate;
    private Instant updatedDate;
}