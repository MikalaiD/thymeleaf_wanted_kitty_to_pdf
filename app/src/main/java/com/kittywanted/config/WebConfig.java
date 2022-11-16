package com.kittywanted.config;

import com.kittywanted.adapters.posterservice.FormattingService;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("/webjars/");
  }

  @Bean
  @Scope(
      value = WebApplicationContext.SCOPE_SESSION,
      proxyMode = ScopedProxyMode.TARGET_CLASS)
  public FormattingService formattingService() {
    var defaultProperties = new HashMap<String, String>();
    defaultProperties.put("theme", "light");
    return new FormattingService(defaultProperties);
  }
}
