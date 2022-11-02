package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.ports.Resolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class SimpleStringResolver implements Resolver<String> {

  @Qualifier("TemplateWithStringResolver")
  private final TemplateEngine templateEngine;

  public String resolve(final String htmlWithThymeleafTags, final Poster poster) {
    final var context = posterToContext(poster);
    return templateEngine.process(htmlWithThymeleafTags, context);
  }

  private Context posterToContext(Poster poster) {
    final var context = new Context();
    context.setVariable("poster", poster);
    return context;
  }
}
