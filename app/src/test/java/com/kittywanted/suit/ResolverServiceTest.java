package com.kittywanted.suit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kittywanted.adapters.posterservice.SimpleStringResolver;
import com.kittywanted.config.PosterConfig;
import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.model.Theme;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import utils.TestScenariosProvider;

@SpringJUnitConfig(classes = PosterConfig.class)
class ResolverServiceTest {

    @Autowired
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
        var poster =Map.entry("poster", Poster.builder().name("Garfield").reward(BigDecimal.valueOf(9999)).build());
        var theme = Map.entry("theme", new Theme(false));
        var map = Map.ofEntries(poster,theme);

        var resolvedOutput = resolver.resolve(input, map);
        assertEquals(StringUtils.trimAllWhitespace(expectedHtmlOutput), StringUtils.trimAllWhitespace(resolvedOutput));
    }
}
