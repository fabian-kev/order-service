package com.fabiankevin.orderserviceapp.core.exceptions;

import com.fabiankevin.domainstarter.exception.DomainException;
import com.fabiankevin.orderserviceapp.core.domain.Item;

public class ItemAlreadyExistsException extends DomainException {

    public ItemAlreadyExistsException(Item item){
        setArgs(item.getName());
    }

}
