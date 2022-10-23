package com.kittywanted.suit;

import com.kittywanted.adapters.ConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterServiceTest {


    @Test
    void service_converts_html_with_thymeleaf_tags_to_string_with_data() {
        var service = new ConverterService();
        var inputHtmlTemplateName = "<!DOCTYPE html>\n" +
                "\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "    <title>Good Thymes Virtual Grocery</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<p th:text=\"${cat_name}\">Fluffy!</p>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";


        var expectedHtmlOutput = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>" +
                "\n" +
                "<head>\n" +
                "    <title>Good Thymes Virtual Grocery</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<p>Garfield</p>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        final String actualOutput = service.renderFromThymeleafHtmlToString(inputHtmlTemplateName);
        assertEquals(StringUtils.trimAllWhitespace(expectedHtmlOutput), StringUtils.trimAllWhitespace(actualOutput));
    }
}
