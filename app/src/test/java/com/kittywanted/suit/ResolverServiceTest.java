package com.kittywanted.suit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kittywanted.adapters.posterservice.SimpleStringResolver;
import com.kittywanted.config.PosterConfig;
import com.kittywanted.domain.model.Poster;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import utils.TestScenariosProvider;

@SpringJUnitConfig(classes = PosterConfig.class)
class ResolverServiceTest {

    @Autowired
    @Qualifier("TemplateWithStringResolver")
    private TemplateEngine templateEngine;

    private SimpleStringResolver resolver;
    @BeforeEach
    void setup(){
        resolver = new SimpleStringResolver(templateEngine);
    }

    @Test
    void service_converts_html_with_thymeleaf_tags_to_string_with_data() throws IOException {
        var input = TestScenariosProvider.getScenario("hello", TestScenariosProvider.ScenarioType.INPUT);
        var expectedHtmlOutput = TestScenariosProvider.getScenario("hello", TestScenariosProvider.ScenarioType.EXPECTED);
        var poster = Poster.builder().name("Garfield").build();

        var resolvedOutput = resolver.resolve(input, poster);
        assertEquals(StringUtils.trimAllWhitespace(expectedHtmlOutput), StringUtils.trimAllWhitespace(resolvedOutput));
    }
}
