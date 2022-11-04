package com.kittywanted.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class Poster {
    private final String name;
    private final BigDecimal reward;
    private final Theme theme;

    public enum Theme{
        DARK,LIGHT
    }
}
