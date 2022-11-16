package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.model.Format;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(
    value = WebApplicationContext.SCOPE_SESSION,
    proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class FormattingService {

  public static final String THEME = "theme";
  public static final String LIGHT = "light";
  public static final String DARK = "dark";
  private Map<String, String> properties;

  public FormattingService(Map<String, String> properties) {
    this.properties = properties;
    properties.put(THEME, LIGHT);
  }

  public Format toDomain() {
    return new Format(properties);
  }

  public void toggleTheme() {
    properties.put(THEME, properties.get(THEME).equals(LIGHT) ? DARK : LIGHT);
  }
}
