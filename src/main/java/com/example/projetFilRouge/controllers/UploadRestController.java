package com.example.projetFilRouge.controllers;

import com.example.projetFilRouge.helper.MovieHelper;
import com.example.projetFilRouge.helper.SerieHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/Upload")
@RequiredArgsConstructor
public class UploadRestController {

    private final MovieHelper movieHelper;
    private final SerieHelper serieHelper;

    @GetMapping("/movies/{number}")
    public void getAllMovies(@PathVariable("number") int number) throws IOException, InterruptedException {
         movieHelper.getAllMovies(number);
    }

    @GetMapping("/series/{number}")
    public void getAllSeries(@PathVariable("number") int number) throws IOException, InterruptedException {
        serieHelper.getAllSeries(number);
    }

    @GetMapping("/series/details/{number}/{numberList}")
    public void getDetailsSeries(@PathVariable("number") int number, @PathVariable("numberList") int numberList) throws IOException, InterruptedException {
        serieHelper.getDetailSeries(number, numberList);
    }
}
