package com.example.projetFilRouge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    private String created_by;

    private int movie_Id;

    private String air_date;

    private int nbr_episodes;

    private String name;

    private String poster_path;

    private double vote_average;



}
