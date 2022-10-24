package com.kittywanted.adapters;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;


public class ConverterService {

    private TemplateEngine templateEngine = new TemplateEngine();
    public String renderFromThymeleafHtmlToString(final String htmlWithThymeleafTags, final Context context){
        StringTemplateResolver  templateResolver = new StringTemplateResolver();
        templateEngine.setTemplateResolver(templateResolver);


        return templateEngine.process(htmlWithThymeleafTags, context);
    }
}
