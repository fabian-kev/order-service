package com.fabiankevin.orderserviceapp.core.guards;

import com.fabiankevin.orderserviceapp.core.exceptions.GuardException;

import java.util.function.BooleanSupplier;

public abstract class BaseGuard<T> {
    protected final T value;

    public BaseGuard(T value) {
        this.value = value;
    }

    protected void against(BooleanSupplier tester, String message) {
        if(tester.getAsBoolean()){
            throw new GuardException(message);
        }
    }

    public void againstNull(String exceptionMessage){
        if(value == null){
            throw new GuardException(exceptionMessage);
        }
    }
}
