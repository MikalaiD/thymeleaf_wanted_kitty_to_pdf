package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.FormattingService;
import com.kittywanted.adapters.posterservice.externalmodel.Poster;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PosterEndpoint {

  public static final String POSTER = "poster";
  public static final String FORMAT = "format";
  private final PosterRenderingFacade posterRenderingFacade;
  private final FormattingService formattingService;

  @GetMapping("/")
  public String getIndex(final Model model) {
      model.addAttribute(POSTER, posterRenderingFacade.getEmptyPoster());
      model.addAttribute(FORMAT, formattingService.getProperties());
    return "html/index";
  }

  @GetMapping(value = "/save-as-pdf",
      produces = MediaType.APPLICATION_PDF_VALUE)
  public @ResponseBody byte[] saveAsPdf(@ModelAttribute final Poster poster) {
    return posterRenderingFacade.getAsPdf(poster, Template.CAT_WANTED);
  }

  @PostMapping("/toggle-theme")
  public String toggleTheme() {
    formattingService.toggleTheme();
    return "redirect:/";
  }

}
