package com.kittywanted.adapters.api;

import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.ports.Resolver;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@RequiredArgsConstructor
public class SimpleStringResolver implements Resolver<String> {

  private final TemplateEngine templateEngine;

  public String resolve(final String htmlWithThymeleafTags, final Poster poster) {
    final var templateResolver = new StringTemplateResolver();
    templateEngine.setTemplateResolver(templateResolver);
    final var context = posterToContext(poster);
    return templateEngine.process(htmlWithThymeleafTags, context);
  }

  private Context posterToContext(Poster poster) {
    final var context = new Context();
    context.setVariable("poster", poster);
    return context;
  }
}
