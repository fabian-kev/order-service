package com.fabiankevin.orderserviceapp.application.web.dto;

import lombok.*;

import java.util.UUID;

@Value
@Builder
public class ItemDto {
    private UUID id;
    private int quantity;
    private String code;
    private String name;
    private String description;
    private AmountDto unitPrice;
    private UUID productId;
}