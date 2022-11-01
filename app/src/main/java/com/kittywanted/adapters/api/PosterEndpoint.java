package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.PosterExternalModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PosterEndpoint {

  private final PosterServiceFacade posterServiceFacade;

  @GetMapping("/")
  public String getIndex(final Model model){
    model.addAttribute("poster", posterServiceFacade.getEmptyPoster());
    return "index";
  }

  @GetMapping("/save-as-pdf")
  public @ResponseBody byte[] saveAsPdf(@ModelAttribute final PosterExternalModel poster){
    return posterServiceFacade.getAsPdf(poster, Template.CAT_WANTED);
  }
}
