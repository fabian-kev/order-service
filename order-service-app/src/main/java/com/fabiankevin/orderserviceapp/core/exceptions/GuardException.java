package com.fabiankevin.orderserviceapp.core.exceptions;

import com.fabiankevin.domainstarter.exception.DomainException;

public class GuardException extends DomainException {
  public GuardException(String message){
      super(message);
  }
}