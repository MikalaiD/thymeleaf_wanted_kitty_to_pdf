package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.PosterExternalModel;
import com.kittywanted.domain.ports.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterServiceFacade {
  private final PosterService posterService;

  public PosterExternalModel getEmptyPoster(){
    return posterService.getEmptyPosterExternal();
  }

}
