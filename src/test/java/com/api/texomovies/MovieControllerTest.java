package com.api.texomovies;

import com.api.texomovies.controller.MovieController;
import com.api.texomovies.entity.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public void should_get_awards_interval_return_status_ok() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/awards-interval"))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("producer")));
    }

    @Test
    public void should_create_movie_return_status_created() throws Exception {
        Movie movie = new Movie(200L, 2023, "any_movie", "any_studio","any_producer", false);
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(movie);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void should_update_movie_return_status_no_content() throws Exception {
        Movie movie = new Movie(null, 2027, "any_movie", "any_studio","any_producer", false);
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(movie);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/movies/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    
    @Test
    public  void should_get_movie_by_id_return_status_ok() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/200"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_delete_movie_return_status_no_content() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/movies/200"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void should_get_all_movies_return_status_ok() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
