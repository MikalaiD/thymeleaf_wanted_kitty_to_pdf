package com.kittywanted.domain.ports;

import com.kittywanted.domain.model.WantedPoster;

import java.math.BigDecimal;

public class PosterService {
    public WantedPoster getEmptyPoster(){
        return WantedPoster.builder().build();
    }
}
