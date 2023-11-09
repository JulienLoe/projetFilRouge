package com.example.projetFilRouge.repositories;

import com.example.projetFilRouge.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
}
