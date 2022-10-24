package com.kittywanted.suit;

import com.kittywanted.adapters.ConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;
import utils.TestScenariosProvider;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterServiceTest {


    @Test
    void service_converts_html_with_thymeleaf_tags_to_string_with_data() throws IOException {
        var service = new ConverterService();
        var input = TestScenariosProvider.getScenario("hello", TestScenariosProvider.ScenarioType.INPUT);
        var expectedHtmlOutput = TestScenariosProvider.getScenario("hello", TestScenariosProvider.ScenarioType.EXPECTED);

        final String actualOutput = service.renderFromThymeleafHtmlToString(input);
        assertEquals(StringUtils.trimAllWhitespace(expectedHtmlOutput), StringUtils.trimAllWhitespace(actualOutput));
    }
}
