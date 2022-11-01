package com.kittywanted.adapters.api.exceptions;

public class TemplateException extends RuntimeException{

  public TemplateException() {
  }

  public TemplateException(String message) {
    super(message);
  }

  public TemplateException(String message, Throwable cause) {
    super(message, cause);
  }
}
