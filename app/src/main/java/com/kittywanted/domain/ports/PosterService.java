package com.kittywanted.domain.ports;

import com.kittywanted.domain.model.Poster;
import com.kittywanted.domain.model.Theme;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Service
@RequiredArgsConstructor
public class PosterService {

    private final Resolver<String> resolver;

    @SneakyThrows
    public boolean saveAsPdfAt(final String template,
                               final Path outputPath,
                               final Poster poster,
                               final Theme theme) {

        var resolvedTemplate = resolveTemplate(template, poster, theme);
        var document = Jsoup.parse(resolvedTemplate);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (OutputStream outputStream = new FileOutputStream(outputPath.toFile())) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        return true;
    }

    @SneakyThrows
    public byte[] getAsPdfByteArray(final String template,
                                    final Poster poster,
                                    final Theme theme){
        var resolvedTemplate = resolveTemplate(template, poster, theme);
        var document = Jsoup.parse(resolvedTemplate);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (var outputStream = new ByteArrayOutputStream();) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }

    private String resolveTemplate (final String template,
                                    final Poster poster,
                                    final Theme theme) {
        final var map = new HashMap<String, Object>();
        map.put("poster", poster);
        map.put("theme", theme);
        return resolver.resolve(template, map);
    }

}
