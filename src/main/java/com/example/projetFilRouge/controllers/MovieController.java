package com.example.projetFilRouge.controllers;


import com.example.projetFilRouge.entities.*;
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

@CrossOrigin(origins = "*")
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
        public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {
            try {
                List<Movie> movies = new ArrayList<>();

//                if (title == null)
//                    tutorialRepository.findAll().forEach(tutorials::add);

                    requestRepository.findByTitleContaining(title).forEach(movies::add);

                if (movies.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(movies, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

//        @GetMapping("/movie")
//        public ResponseEntity<List<Movie>> getAllMovie(@RequestParam(required = false) String title) {
//            try {
//                List<Movie> movies = new ArrayList<>();
//
//                requestRepository.findAllCast().forEach(movies::add);
//
//                if (movies.isEmpty()) {
//                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//                }
//
//                return new ResponseEntity<>(movies, HttpStatus.OK);
//            } catch (Exception e) {
//                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }

        @GetMapping("/cast")
        public ResponseEntity<List<Cast>> getAllCast(@RequestParam(required = false) String title) {
            try {
                List<Cast> cast = new ArrayList<>();

                requestRepository.findAllCast().forEach(cast::add);

                if (cast.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(cast, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/episode")
        public ResponseEntity<List<Episode>> getAllEpisode(@RequestParam(required = false) String title) {
            try {
                List<Episode> episodes = new ArrayList<>();

                requestRepository.findAllEpisode().forEach(episodes::add);

                if (episodes.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(episodes, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/season")
        public ResponseEntity<List<Season>> getAllSeason(@RequestParam(required = false) String title) {
            try {
                List<Season> seasons = new ArrayList<>();

                requestRepository.findAllSeason().forEach(seasons::add);

                if (seasons.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(seasons, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/serie")
        public ResponseEntity<List<Serie>> getAllSerie(@RequestParam(required = false) String title) {
            try {
                List<Serie> series = new ArrayList<>();

                requestRepository.findAllSerie().forEach(series::add);

                if (series.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(series, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

//        @GetMapping("/category")
//        public ResponseEntity<List<Category>> getAllMovie(@RequestParam(required = false) String title) {
//            try {
//                List<Category> categories = new ArrayList<>();
//
//                requestRepository.findAllCast().forEach(categories::add);
//
//                if (categories.isEmpty()) {
//                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//                }
//
//                return new ResponseEntity<>(categories, HttpStatus.OK);
//            } catch (Exception e) {
//                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }

        @GetMapping("/movies/{number}")
        public void getAllMovies(@PathVariable("number") int number) throws IOException, InterruptedException {
            movieHelper.getAllMovies(number);
        }

}
