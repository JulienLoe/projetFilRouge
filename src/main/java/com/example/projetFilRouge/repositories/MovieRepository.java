package com.example.projetFilRouge.repositories;

import com.example.projetFilRouge.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {


}
