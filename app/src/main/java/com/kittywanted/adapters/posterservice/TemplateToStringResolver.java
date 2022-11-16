package com.kittywanted.adapters.posterservice;

import com.kittywanted.domain.ports.Resolver;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class TemplateToStringResolver implements Resolver<String> {

  private final TemplateEngine templateEngine;

  public String resolve(final String htmlWithThymeleafTags, final Map<String, Object> objects ) {
    final var context = createContextFrom(objects);
    return templateEngine.process(htmlWithThymeleafTags, context);
  }

  private Context createContextFrom(final Map<String, Object> objects) {
    final var context = new Context();
    context.setVariables(objects);
    return context;
  }
}
