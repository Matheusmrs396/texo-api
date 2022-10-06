package com.api.texomovies.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Table(name="movies")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int years;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String studios;
    @Column(nullable = false)
    private String producers;
    @Column(nullable = false)
    private boolean winner;
}
