package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.PosterExternalModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class PosterEndpoint {

  private final PosterServiceFacade posterServiceFacade;

  @GetMapping("/")
  public String getIndex(final Model model) {
    model.addAttribute("poster", posterServiceFacade.getEmptyPoster());
    return "html/index";
  }

  @GetMapping(value = "/save-as-pdf",
      produces = MediaType.APPLICATION_PDF_VALUE)
  public @ResponseBody byte[] saveAsPdf(@ModelAttribute final PosterExternalModel poster) {
    return posterServiceFacade.getAsPdf(poster, Template.CAT_WANTED);
  }

  @PostMapping("/toggle-theme")
  @ResponseStatus(value = HttpStatus.OK)
  public void toggleTheme(@ModelAttribute final PosterExternalModel poster) {
    posterServiceFacade.toggleTheme(poster);
  }

}
