package com.api.texomovies.controller;

import com.api.texomovies.entity.Movie;
import com.api.texomovies.payload.ProducerAward;
import com.api.texomovies.payload.Result;
import com.api.texomovies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/awards-interval")
    @ResponseStatus(HttpStatus.OK)
    public Result awardsInterval(){
        Map<String,List<Integer>> yearByProducers = new HashMap<>();
        List<Movie> movies = movieRepository.findAll();
        List<Movie> moviesWinner = new ArrayList<>();
        for(Movie movie : movies){
            if(!movie.isWinner()){
                continue;
            }
            moviesWinner.add(movie);
            List<String> producers = Arrays.asList(movie.getProducers().split("(,\\s|\\sand\\s)"));
            for(String producer : producers){
                List<Integer> years = yearByProducers.getOrDefault(producer.trim(), new ArrayList<>());
                years.add(movie.getYears());
                yearByProducers.put(producer.trim(),years);
            }
        }
        List<ProducerAward> producerAwards = yearByProducers.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(entry -> new ProducerAward(entry.getKey(),
                        entry.getValue().get(entry.getValue().size()-1) - entry.getValue().get(0),
                        entry.getValue().get(0), entry.getValue().get(entry.getValue().size()-1)))
                .toList();
        int minInterval = producerAwards.stream().map(ProducerAward::interval).min(Integer::compare).get();
        int maxInterval = producerAwards.stream().map(ProducerAward::interval).max(Integer::compare).get();
        List<ProducerAward> min = producerAwards.stream().filter(p->p.interval() == minInterval).toList();
        List<ProducerAward> max = producerAwards.stream().filter(p->p.interval() == maxInterval).toList();;


        return new Result(min, max);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie saveMovie(@RequestBody Movie movie){

        return movieRepository.save(movie);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> listAllMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Movie> movieById(@PathVariable("id") Long  id){
            return Optional.ofNullable(movieRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "movie doesn't exist")));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@PathVariable("id") Long  id){
        movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.deleteById(id);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "movie doesn't exist"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovieById(@PathVariable("id") Long  id, @RequestBody Movie movie){
        movieRepository.findById(id)
                .map(movieBase -> {
                    modelMapper.map(movie, movieBase);
                    movieRepository.save(movieBase);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "movie doesn't exist"));
    }
}
