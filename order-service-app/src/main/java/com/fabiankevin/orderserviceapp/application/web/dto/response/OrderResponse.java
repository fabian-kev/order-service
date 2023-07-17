package com.fabiankevin.orderserviceapp.application.web.dto.response;

import com.fabiankevin.orderserviceapp.application.web.dto.AmountDto;
import com.fabiankevin.orderserviceapp.core.domain.OrderStatus;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class OrderResponse {

    private UUID id;
    private UUID customerId;
    private List<Item> items;
    private String note;
    private String currency;
    private OrderStatus status;
    private Instant createdDate;
    private Instant updatedDate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Item {
        private UUID id;
        private int quantity;
        private String code;
        private String name;
        private String description;
        private AmountDto unitPrice;
        private UUID productId;
    }
}