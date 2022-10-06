package com.api.texomovies;

import com.api.texomovies.entity.Movie;
import com.api.texomovies.repository.MovieRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Slf4j
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("classpath:movielist.csv")
    private Resource movielist;

    private final MovieRepository movieRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
            CSVReader reader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(movielist.getInputStream())))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();
            String[] line;
            while (( line = reader.readNext()) != null){
                Movie movie = Movie.builder()
                        .years(Integer.parseInt(line[0]))
                        .title(line[1])
                        .studios(line[2])
                        .producers(line[3])
                        .winner(!StringUtils.isBlank(line[4]))
                        .build();
                movieRepository.save(movie);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
