package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.ports.Resolver;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
public class SimpleStringResolver implements Resolver<String> {

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
