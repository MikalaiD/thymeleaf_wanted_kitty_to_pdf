package com.kittywanted.suit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kittywanted.adapters.api.PosterEndpoint;
import com.kittywanted.adapters.api.PosterRenderingFacade;
import com.kittywanted.adapters.api.Template;
import com.kittywanted.adapters.posterservice.FormattingService;
import com.kittywanted.adapters.posterservice.externalmodel.Poster;
import java.math.BigDecimal;
import java.util.HashMap;
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
  private PosterRenderingFacade posterRenderingFacade;

  @MockBean
  private FormattingService formattingService;

  private Poster poster;

  @BeforeEach
  void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    poster = Poster.builder()
                   .name("Fluffy")
                   .reward(BigDecimal.valueOf(876L))
                   .build();
  }

  @Test
  void testMainPageReturnedWithAllAttributes() throws Exception {

    final Poster poster = Poster.builder().build();

    when(posterRenderingFacade.getEmptyPoster()).thenReturn(poster);

    mockMvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(model().attribute("poster", poster))
           .andExpect(model().attributeExists("format"));
  }

  @Test
  void testPdfSaveIsCalledCorrectly() throws Exception {
    when(posterRenderingFacade.getAsPdf(any(Poster.class), any(Template.class)))
        .thenReturn(new byte[]{});

    mockMvc.perform(get("/save-as-pdf").flashAttr("poster", poster))
           .andExpect(status().isOk());
  }
}
