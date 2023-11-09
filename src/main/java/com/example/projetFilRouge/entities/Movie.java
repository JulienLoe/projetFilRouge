package com.example.projetFilRouge.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private boolean adult;

    @Column(length = 1200)
    private String backdrop_path;

    @Column(length = 1200)
    private String genre_ids;

    private int idDb;

    @Column(length = 1200)
    private String original_language;

    @Column(length = 1200)
    private String original_title;

    @Column(length = 1200)
    private String overview;

    private double popularity;

    @Column(length = 1200)
    private String poster_path;

    @Column(length = 1200)
    private String release_date;

    @Column(length = 1200)
    private String title;

    private boolean video;
    private double vote_average;
    private int vote_count;
}
