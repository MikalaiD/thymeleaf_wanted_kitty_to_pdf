package com.kittywanted.adapters.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PosterEndpoint {

  private final PosterServiceFacade posterServiceFacade;

  @GetMapping("/")
  public String getIndex(final Model model){
    model.addAttribute("poster", posterServiceFacade.getEmptyPoster());
    return "index";
  }
}
