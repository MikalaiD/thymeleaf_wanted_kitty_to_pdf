package com.kittywanted.suit;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kittywanted.config.PosterConfig;
import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.ports.PosterService;
import com.kittywanted.domain.ports.Resolver;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import utils.TestScenariosProvider;

@SpringJUnitConfig(classes = PosterConfig.class)
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
    assertNotNull(posterService.getEmptyPoster());
  }

  @SneakyThrows
  @Test
  void testPosterSavedAsPdfAndContainsAllData() {
    //given
    var poster = Poster.builder()
                       .name("Fluffy")
                       .ownerName("Nick").reward(BigDecimal.valueOf(700))
                       .build();

    var outputPath = tempDir.resolve("poster_all.pdf");

    var template = TestScenariosProvider.getScenario("all_data_has_placeholder",
                                                     TestScenariosProvider.ScenarioType.INPUT);

    assertTrue(posterService.saveAsPdf(poster, template, outputPath));
    assertTrue(pdfContainsKeyValues(outputPath, poster));
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
    return Stream.of(poster.getName(), poster.getOwnerName(),
                     poster.getReward() == null ? null : poster.getReward().toEngineeringString());
  }

}