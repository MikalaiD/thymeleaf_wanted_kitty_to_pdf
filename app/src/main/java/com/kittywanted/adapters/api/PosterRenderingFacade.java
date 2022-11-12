package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.externalmodel.Poster;
import com.kittywanted.adapters.posterservice.externalmodel.Theme;
import com.kittywanted.domain.ports.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterRenderingFacade {
  private final PosterService posterService;
  private final Theme theme;

  public Poster getEmptyPoster(){
    return posterService.getEmptyPosterExternal();
  } //TODO refactor - move from service to facade

  public byte[] getAsPdf(final Poster poster,
                       final Template template) {
    return posterService.getAsPdfByteArray(poster.toDomain(),
                                           template.toString(),
                                           theme.toDomain());

  }
}
