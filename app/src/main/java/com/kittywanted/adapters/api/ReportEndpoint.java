package com.kittywanted.adapters.api;

import com.kittywanted.domain.model.Poster;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportEndpoint {
  @GetMapping("/")
  public String test(final Model model){
    model.addAttribute("poster", Poster.builder().name("Cat1").ownerName("Noone").reward(BigDecimal.ZERO).build());
    return "index";
  }
}
