package com.kittywanted.config;

import java.util.Map;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.linkbuilder.StandardLinkBuilder;

public class CustomLinkBuilder extends StandardLinkBuilder {

  //Default implementation does not allow relative paths for IContext - only to IWebContext

  @Override
  protected String computeContextPath(
      final IExpressionContext context, final String base, final Map<String, Object> parameters) {

    if (context instanceof IWebContext) {
      return super.computeContextPath(context, base, parameters);
    }

    return "./app/src/main/resources/static";

  }


}
