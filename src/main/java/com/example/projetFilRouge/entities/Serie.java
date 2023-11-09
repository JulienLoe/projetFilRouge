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
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 1200)
    private String backdrop_path;

    @Column(length = 1200)
    private String first_air_date;

    @Column(length = 1200)
    private String genre_ids;

    private int idDb;

    @Column(length = 1200)
    private String name;

    @Column(length = 1200)
    private String origin_country;

    @Column(length = 1200)
    private String original_language;

    @Column(length = 1200)
    private String original_name;

    @Column(length = 1200)
    private String overview;

    private double popularity;

    @Column(length = 1200)
    private String poster_path;


    private double vote_average;
    private int vote_count;

    private int nbr_seasons;
    private int nbr_episodes;
    private int page;


}
