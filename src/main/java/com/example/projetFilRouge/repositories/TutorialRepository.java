package com.example.projetFilRouge.repositories;


import com.example.projetFilRouge.models.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {
}
