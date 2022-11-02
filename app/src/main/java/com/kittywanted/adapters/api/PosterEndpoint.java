package com.kittywanted.adapters.api;

import com.kittywanted.adapters.posterservice.PosterExternalModel;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
  public String getIndex(final Model model) {
    model.addAttribute("poster", posterServiceFacade.getEmptyPoster());
    return "index";
  }

  @GetMapping(value = "/save-as-pdf",
      produces = MediaType.APPLICATION_PDF_VALUE)
  public @ResponseBody byte[] saveAsPdf(@ModelAttribute final PosterExternalModel poster) {

    PosterExternalModel stub = PosterExternalModel.builder().name("Volvo").ownerName("Nick")
                                                   .reward(BigDecimal.valueOf(3000)).build();
    return posterServiceFacade.getAsPdf(stub, Template.CAT_WANTED);
  }
}
