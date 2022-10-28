package com.kittywanted.config;

import com.kittywanted.adapters.api.SimpleStringResolver;
import com.kittywanted.domain.ports.PosterService;
import com.kittywanted.domain.ports.Resolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

@Configuration
public class PosterConfig {

    @Bean
    public PosterService posterService(final Resolver<String> resolver){
        return new PosterService(resolver);
    }

    @Bean
    public Resolver<String> converterService(final TemplateEngine templateEngine){
        return new SimpleStringResolver(templateEngine);
    }

    @Bean
    public TemplateEngine templateEngine(){
        return new TemplateEngine();
    }
}
