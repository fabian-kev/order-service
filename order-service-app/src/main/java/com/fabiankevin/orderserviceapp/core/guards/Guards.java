package com.fabiankevin.orderserviceapp.core.guards;

public class Guards {
    public static StringGuard guard(String value) {
        return new StringGuard(value);
    }

    public static IntGuard guard(Integer value) {
        return new IntGuard(value);
    }

    public static ObjectGuard guard(Object value) {
        return new ObjectGuard(value);
    }

}
