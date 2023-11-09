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
@Table(name = "casting")
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private int movieId;

    @Column(length = 1200)
    private boolean adult;

    private int gender;
    private int idDb;

    @Column(length = 1200)
    private String known_for_department;

    @Column(length = 1200)
    private String name;

    @Column(length = 1200)
    private String original_name;

    private double popularity;

    @Column(length = 1200)
    private String profile_path;

    private int cast_id;

    @Column(length = 1200)
    private String character;

    @Column(length = 1200)
    private String credit_id;

    @Column(name = "rang")
    private int order;


    private boolean ofSerie = false;
}
