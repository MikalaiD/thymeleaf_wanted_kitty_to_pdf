package com.kittywanted.adapters.posterservice.externalmodel;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(
    value = WebApplicationContext.SCOPE_SESSION,
    proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Theme {
  private boolean dark;
  public com.kittywanted.domain.model.Theme toDomain(){
    return new com.kittywanted.domain.model.Theme(dark);
  }
  public void toggle(){
    dark = !dark;
  }
}
