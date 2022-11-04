package com.kittywanted.suit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kittywanted.adapters.api.PosterEndpoint;
import com.kittywanted.adapters.api.PosterServiceFacade;
import com.kittywanted.adapters.api.Template;
import com.kittywanted.adapters.posterservice.PosterExternalModel;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = PosterEndpoint.class)
class PosterEndpointTests {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private PosterServiceFacade posterServiceFacade;
  private PosterExternalModel poster;

  @BeforeEach
  void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    poster = PosterExternalModel.builder()
                                .name("Fluffy")
                                .ownerName("John Wick")
                                .reward(BigDecimal.valueOf(876L))
                                .build();
  }

  @Test
  void testMainPageReturnedWithAllAttributes() throws Exception {

    final PosterExternalModel poster = PosterExternalModel.builder().build();

    when(posterServiceFacade.getEmptyPoster()).thenReturn(poster);

    mockMvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(model().attribute("poster", poster));
  }

  @Test
  void testPdfSaveIsCalledCorrectly() throws Exception {
    final PosterExternalModel poster = PosterExternalModel.builder()
                                                          .name("Fluffy")
                                                          .ownerName("John Wick")
                                                          .reward(BigDecimal.valueOf(876L))
                                                          .build();
    when(posterServiceFacade.getAsPdf(any(PosterExternalModel.class), any(Template.class)))
        .thenReturn(new byte[]{});

    mockMvc.perform(get("/save-as-pdf").flashAttr("poster", poster))
           .andExpect(status().isOk());
  }

  @Test
  void testTogglingPosterTheme() throws Exception {

    doNothing().when(posterServiceFacade).toggleTheme(poster);

    mockMvc.perform(post("/toggle-theme")).andExpect(status().isOk());
  }
}
