package com.fabiankevin.orderserviceapp.core.usecases.inbound;

import com.fabiankevin.domainstarter.domain.Amount;

import java.util.UUID;

public record ItemCommand(
        int quantity,
        String code,
        String name,
        String description,
        Amount unitPrice,
        UUID productId
) { }




