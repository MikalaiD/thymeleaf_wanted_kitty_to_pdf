package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.externalmodel.Poster;
import com.kittywanted.adapters.posterservice.externalmodel.Theme;
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
  public static final String THEME = "theme";
  private final PosterRenderingFacade posterRenderingFacade;
  private final Theme activeTheme;

  @GetMapping("/")
  public String getIndex(final Model model) {
      model.addAttribute(POSTER, posterRenderingFacade.getEmptyPoster());
      model.addAttribute(THEME, activeTheme);
    return "html/index";
  }

  @GetMapping(value = "/save-as-pdf",
      produces = MediaType.APPLICATION_PDF_VALUE)
  public @ResponseBody byte[] saveAsPdf(@ModelAttribute final Poster poster) {
    return posterRenderingFacade.getAsPdf(poster, Template.CAT_WANTED);
  }

      @PostMapping("/toggle-theme")
  public String toggleTheme() {
    activeTheme.toggle();
    return "redirect:/";
  }

}
