package com.kittywanted.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class WantedPoster {
    private final String name;
    private final BigDecimal price;
    private final String ownerName;
}
