package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.FormattingService;
import com.kittywanted.adapters.posterservice.externalmodel.Poster;
import com.kittywanted.domain.ports.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterRenderingFacade {

  private final PosterService posterService;
  private final FormattingService formattingService;

  public Poster getEmptyPoster() {
    return Poster.builder().build();
  }

  public byte[] getAsPdf(final Poster poster,
                         final Template template) {
    return posterService.getAsPdfByteArray(template.toString(),
                                           poster.toDomain(),
                                           formattingService.toDomain());

  }
}
