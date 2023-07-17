package com.fabiankevin.orderserviceapp.core.domain.value;

import java.util.Objects;

public final class Currency {

    private String value;

    public Currency(String value) {
        if(!java.util.Currency.getAvailableCurrencies().contains(java.util.Currency.getInstance(value))){
            throw new IllegalArgumentException("Invalid currency. currency="+value);
        }
        this.value = value;
    }

    public static Currency PHP(){
        return new Currency("PHP");
    }

    public String value(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(value, currency.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
