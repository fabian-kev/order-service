package com.fabiankevin.orderserviceapp.core.domain.value;

import com.fabiankevin.orderserviceapp.core.guards.Guards;

import java.util.Optional;

public final class Quantity {
    private static final int MAX_QUANTITY = 100;
    private static final int MIN_QUANTITY = 1;
    private Integer value;

    public Quantity(Integer value) {
        this.value = Optional.ofNullable(value).orElse(1);

        Guards.guard(value).againstMinimumMaximum(MIN_QUANTITY, MAX_QUANTITY,
                String.format("Quantity should be in range of %s to %s", MIN_QUANTITY, MAX_QUANTITY));
    }

    public Quantity() {
        this(1);
    }

    public Integer getValue() {
        return value;
    }
}
