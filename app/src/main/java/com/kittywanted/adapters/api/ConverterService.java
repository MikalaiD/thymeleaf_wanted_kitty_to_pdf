package com.kittywanted.adapters.api;

import com.kittywanted.domain.model.Poster;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;


public class ConverterService {

    private TemplateEngine templateEngine = new TemplateEngine();
    public String resolve(final String htmlWithThymeleafTags, final Poster poster){
        StringTemplateResolver  templateResolver = new StringTemplateResolver();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = posterToContext(poster);
        return templateEngine.process(htmlWithThymeleafTags, context);
    }

    private Context posterToContext(Poster poster) {
        final Context context = new Context();
        context.setVariable("poster", poster);
        return context;
    }
}
