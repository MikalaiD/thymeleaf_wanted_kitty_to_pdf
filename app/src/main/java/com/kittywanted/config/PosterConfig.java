package com.kittywanted.config;

import com.kittywanted.adapters.posterservice.SimpleStringResolver;
import com.kittywanted.domain.ports.PosterService;
import com.kittywanted.domain.ports.Resolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class PosterConfig {

    @Bean
    public PosterService posterService(final Resolver<String> resolver){
        return new PosterService(resolver);
    }

    @Bean
    public Resolver<String> resolver(final TemplateEngine templateEngine){
        return new SimpleStringResolver(templateEngine);
    }

    @Bean(name = "TemplateWithStringResolver") //TODO template with this resolver is the default one, if no other is used later - delete this code
    public TemplateEngine templateEngine(){
        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        return templateEngine;
    }
}
