package com.api.texomovies.controller;

import com.api.texomovies.entity.Movie;
import com.api.texomovies.payload.ProducerAward;
import com.api.texomovies.payload.Result;
import com.api.texomovies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;
    @GetMapping("/awards-interval")
    public Result awardsInterval(){
        Map<String,List<Integer>> yearByProducers = new HashMap<>();
        List<Movie> movies = movieRepository.findAll();
        for(Movie movie : movies){
            if(!movie.isWinner()){
                continue;
            }
            List<String> producers = Arrays.asList(movie.getProducers().split(","));
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
}