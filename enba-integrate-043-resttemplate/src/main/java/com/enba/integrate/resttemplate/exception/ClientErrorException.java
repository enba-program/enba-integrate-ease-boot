package com.enba.integrate.resttemplate.exception;

public class ClientErrorException extends RuntimeException {
  public ClientErrorException(String message) {
    super(message);
  }
}
