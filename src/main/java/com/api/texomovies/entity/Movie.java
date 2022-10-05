package com.api.texomovies.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String year;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String studios;
    @Column(nullable = false)
    private String producers;
    @Column(nullable = false)
    private Boolean winner;
}
