package com.kittywanted.suit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kittywanted.adapters.api.PosterEndpoint;
import com.kittywanted.adapters.api.PosterServiceFacade;
import com.kittywanted.adapters.posterservice.PosterExternalModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = PosterEndpoint.class)
class IndexMVCTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private PosterServiceFacade posterServiceFacade;

  @BeforeEach
  void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void testMainPageReturnedWithAllAttributes() throws Exception {

    final PosterExternalModel poster = PosterExternalModel.builder().build();

    when(posterServiceFacade.getEmptyPoster()).thenReturn(poster);

    mockMvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(model().attribute("poster", poster));
  }
}
