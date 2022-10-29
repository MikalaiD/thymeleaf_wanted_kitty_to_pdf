package com.kittywanted.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class PosterConfig {

    @Bean(name = "TemplateWithStringResolver") //TODO template with this resolver is the default one, if no other is used later - delete this code
    public TemplateEngine templateEngine(){
        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        return templateEngine;
    }
}
