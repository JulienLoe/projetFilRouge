package com.example.projetFilRouge.controllers;


import com.example.projetFilRouge.helper.MovieHelper;
import com.example.projetFilRouge.models.Tutorial;
import com.example.projetFilRouge.repositories.RequestRepository;
import com.example.projetFilRouge.repositories.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
    @RestController
    @RequestMapping("/api")
    @RequiredArgsConstructor
    public class MovieController {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    RequestRepository requestRepository;

    private final MovieHelper movieHelper;

        @GetMapping("/movies")
        public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
            try {
                List<Tutorial> tutorials = new ArrayList<>();

//                if (title == null)
//                    tutorialRepository.findAll().forEach(tutorials::add);

                    requestRepository.findByTitleContaining(title).forEach(tutorials::add);

                if (tutorials.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(tutorials, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/movies/{number}")
        public void getAllMovies(@PathVariable("number") int number) throws IOException, InterruptedException {
            movieHelper.getAllMovies(number);
        }

}
