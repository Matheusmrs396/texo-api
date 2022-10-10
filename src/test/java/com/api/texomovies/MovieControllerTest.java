package com.api.texomovies;

import com.api.texomovies.controller.MovieController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MovieControllerTest extends TexoMoviesApplicationTests{
    private MockMvc mockMvc;

    @Autowired
    MovieController movieController;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void should_return_status_ok() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/awards-interval"))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("producer")));
    }
}
