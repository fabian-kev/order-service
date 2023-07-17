package com.fabiankevin.orderserviceapp.core.guards;

public class StringGuard extends BaseGuard<String> {
    public StringGuard(String value) {
        super(value);
    }

    public void againstNullOrEmpty(String message){
        againstNull(value);
        against(value::isEmpty, message);
    }

    public void againstShortOrLong(int min, int max, String message){
        against(() ->  value.length() < min || value.length() > max, message);
    }

    public void againstLongEnough(int max, String message){
        against(() ->  value.length() > max, message);
    }
}
