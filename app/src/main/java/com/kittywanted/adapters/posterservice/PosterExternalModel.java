package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.model.Poster;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PosterExternalModel {

  private final String name;
  private final String ownerName;
  private final BigDecimal reward;

  public Poster toDomain(){
    return Poster.builder().name(this.name)
        .ownerName(ownerName)
        .reward(reward)
        .build();
  }
}
