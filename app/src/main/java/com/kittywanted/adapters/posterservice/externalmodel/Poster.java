package com.kittywanted.adapters.posterservice.externalmodel;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Poster {

  private String name;
  private BigDecimal reward;

  public com.kittywanted.domain.model.Poster toDomain() {
    return com.kittywanted.domain.model.Poster.builder().name(this.name)
                                              .reward(this.reward)
                                              .build();
  }
}
