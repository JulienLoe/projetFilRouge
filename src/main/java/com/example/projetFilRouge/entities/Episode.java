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
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(length = 2500)
    private String air_date;

    private int episode_number;

    @Column(length = 2500)
    private String episode_type;

    private int idDb;

    @Column(length = 2500)
    private String name;

    @Column(length = 2500)
    private String overview;

    @Column(length = 2500)
    private String production_code;

    private int runtime;

    private int season_number;

    private int show_id;

    @Column(length = 2500)
    private String still_path;

    private double vote_average;
    private int vote_count;
}
