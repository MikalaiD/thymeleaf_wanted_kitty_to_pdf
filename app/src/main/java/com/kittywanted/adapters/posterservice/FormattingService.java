package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.model.Format;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class FormattingService {

  public static final String THEME = "theme";
  public static final String LIGHT = "light";
  public static final String DARK = "dark";
  private final Map<String, String> properties;

  public Format toDomain() {
    return new Format(properties);
  }

  public void toggleTheme() {
    properties.put(THEME, properties.get(THEME).equals(LIGHT) ? DARK : LIGHT);
  }
}
