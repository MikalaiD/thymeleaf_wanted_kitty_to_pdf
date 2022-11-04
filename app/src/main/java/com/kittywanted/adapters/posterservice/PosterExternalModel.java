package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.model.Poster;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PosterExternalModel {

  private String name;
  private Theme theme;
  private BigDecimal reward;

  public Poster toDomain() {
    return Poster.builder().name(this.name)
                 .theme(Poster.Theme.valueOf(theme.toString()))
                 .reward(reward)
                 .build();
  }

  public void toggleTheme() {
    theme = theme == Theme.LIGHT ? Theme.DARK : Theme.LIGHT;
  }

  public enum Theme {
    DARK, LIGHT
  }
}
