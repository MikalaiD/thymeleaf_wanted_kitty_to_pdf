package com.kittywanted.suit;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kittywanted.adapters.posterservice.SimpleStringResolver;
import com.kittywanted.config.PosterConfig;
import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.model.Poster.Theme;
import com.kittywanted.domain.ports.PosterService;
import com.kittywanted.domain.ports.Resolver;
import com.kittywanted.suit.PosterServiceTest.LocalConfig;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.thymeleaf.TemplateEngine;
import utils.TestScenariosProvider;

@SpringJUnitConfig(classes = PosterConfig.class)
@Import(LocalConfig.class)
class PosterServiceTest {

  @TempDir
  Path tempDir = Path.of("./temp/output");
  @Autowired
  private Resolver<String> resolver;
  private PosterService posterService;

  @BeforeEach
  void setup() {
    posterService = new PosterService(resolver);
  }

  @Test
  void testPosterServiceReturnsEmptyReport() {
    var emptyPosterExternal = posterService.getEmptyPosterExternal();
    assertNotNull(emptyPosterExternal);
    assertNull(emptyPosterExternal.getName());
    assertNull(emptyPosterExternal.getTheme());
    assertNull(emptyPosterExternal.getReward());
  }

  @SneakyThrows
  @Test
  void testPosterSavedAsPdfAndContainsAllData() {
    //given
    var poster = Poster.builder()
                       .name("Fluffy")
                       .theme(Theme.LIGHT).reward(BigDecimal.valueOf(700))
                       .build();

    var outputPath = tempDir.resolve("poster_all.pdf");
    var template = TestScenariosProvider.getScenario("all_data_has_placeholder",
                                                     TestScenariosProvider.ScenarioType.INPUT);

    assertTrue(posterService.saveAsPdfAt(poster, template, outputPath));
    assertTrue(pdfContainsKeyValues(outputPath, poster));
  }

  @Test
  @SneakyThrows
  void testIfPdfByteArrayIsReturned(){

    var poster = Poster.builder()
                       .name("Fluffy")
                       .theme(Theme.LIGHT)
                       .reward(BigDecimal.valueOf(700))
                       .build();
    var template = TestScenariosProvider.getScenario("all_data_has_placeholder",
                                                     TestScenariosProvider.ScenarioType.INPUT);

    assertNotNull(posterService.getAsPdfByteArray(poster, template));
  }

  private boolean pdfContainsKeyValues(final Path path, final Poster poster) {
    var file = path.toFile();
    try (var randomAccessFile = new RandomAccessFile(file, "r")) {
      var pdfParser = new PDFParser(randomAccessFile);
      pdfParser.parse();
      var cosDoc = pdfParser.getDocument();
      var pdfStripper = new PDFTextStripper();
      try (var pdDoc = new PDDocument(cosDoc)) {
        var parsedText = pdfStripper.getText(pdDoc);
        return extractAttributes(poster).filter(Objects::nonNull).allMatch(parsedText::contains);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Stream<String> extractAttributes(final Poster poster) {
    return Stream.of(poster.getName(),
                     poster.getTheme() == null ? null : poster.getTheme().name(),
                     poster.getReward() == null ? null : poster.getReward().toEngineeringString());
  }

  @TestConfiguration
  static class LocalConfig{
    @Bean
    public Resolver<String> resolver(final TemplateEngine templateEngine){
      return new SimpleStringResolver(templateEngine);
    }
  }
}