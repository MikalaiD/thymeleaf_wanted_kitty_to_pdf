package com.kittywanted.suit;


import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.ports.PosterService;
import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import utils.TestScenariosProvider;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PosterServiceTest {

    @TempDir
    Path tempDir = Path.of("./temp/output");

    @Test
    void testPosterServiceReturnsEmptyReport() {
        PosterService posterService = new PosterService();
        assertNotNull(posterService.getEmptyPoster());
    }

    @SneakyThrows
    @Test
    void testPosterSavedAsPdfAndContainsAllData() {
        //given
        PosterService posterService = new PosterService();
        Poster poster = Poster.builder().name("Fluffy")
                .ownerName("Nick")
                .reward(BigDecimal.valueOf(700))
                .build();

        var path = tempDir.resolve("poster_all.pdf");

        var template = TestScenariosProvider.getScenario("all_data_has_placeholder", TestScenariosProvider.ScenarioType.INPUT);

        assertTrue(posterService.saveAsPdf(poster,template, path));
        assertTrue(pdfContainsKeyValues(path, poster));
    }

    private boolean pdfContainsKeyValues(final Path path, final Poster poster) {
        File file = path.toFile();
        String parsedText;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")){
            PDFParser parser = new PDFParser(randomAccessFile);
            parser.parse();
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            try(PDDocument pdDoc = new PDDocument(cosDoc)) {
                parsedText = pdfStripper.getText(pdDoc);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return extractAttributes(poster).filter(Objects::nonNull).allMatch(parsedText::contains);
    }

    private Stream<String> extractAttributes(final Poster poster) {
        return Stream.of(poster.getName(),
                poster.getOwnerName(),
                poster.getReward() == null ? null : poster.getReward().toEngineeringString());
    }

}