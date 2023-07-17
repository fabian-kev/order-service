package com.fabiankevin.orderserviceapp.core.guards;

public class IntGuard extends BaseGuard<Integer> {
    public IntGuard(Integer value) {
        super(value);
    }

    public void againstMinimumMaximum(int min, int max, String errorMessage){
        against(() -> value < min || value > max, errorMessage);
    }
}
