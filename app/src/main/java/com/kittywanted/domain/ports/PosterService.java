package com.kittywanted.domain.ports;

import com.kittywanted.adapters.posterservice.PosterExternalModel;
import com.kittywanted.domain.model.Poster;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
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
    public PosterExternalModel getEmptyPosterExternal(){
        return PosterExternalModel.builder().build();
    }

    @SneakyThrows
    public boolean saveAsPdf(final Poster poster, final String template, final Path outputPath) {

        final String resolvedTemplate = resolver.resolve(template, poster);
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
}
