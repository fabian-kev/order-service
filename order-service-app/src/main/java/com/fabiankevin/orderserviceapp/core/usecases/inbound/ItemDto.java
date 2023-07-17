package com.fabiankevin.orderserviceapp.core.usecases.inbound;

import com.fabiankevin.domainstarter.domain.Amount;

import java.util.UUID;

public record ItemDto(
        UUID id,
        int quantity,
        String code,
        String name,
        String description,
        Amount unitPrice,
        UUID productId
) { }