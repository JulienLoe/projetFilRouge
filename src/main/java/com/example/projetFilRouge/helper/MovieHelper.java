package com.example.projetFilRouge.helper;

import com.example.projetFilRouge.entities.Cast;
import com.example.projetFilRouge.entities.Movie;
import com.example.projetFilRouge.repositories.CastRepository;
import com.example.projetFilRouge.repositories.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
@RequiredArgsConstructor
public class MovieHelper {

//    private final RestTemplateBuilder builder;

    private final MovieRepository movieRepository;
    private final CastRepository castRepository;


    public void getAllMovies(int number) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=fr&sort_by=popularity.desc&page="  + number))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYTc5ZDI1OWFjNWZhYTM4ZTcwNDdkYjlkMWEzYWNjYiIsInN1YiI6IjY1MzZkMDM4OTQ2MzE4MDBhZDk0MDA1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6f5J39O2-Irt0iTC4TDXsiEdCTChCARLF7yvx-72_rE")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response.body());

        // Accéder aux résultats
        JsonNode results = jsonResponse.get("results");


        // Parcourir les résultats et afficher des informations
        for (JsonNode result : results) {

            Movie movie = new Movie();
            movie.setAdult(result.get("adult").asBoolean());
            movie.setBackdrop_path(result.get("backdrop_path").asText());
            movie.setGenre_ids(result.get("genre_ids").toString());
            movie.setIdDb(result.get("id").asInt());
            int idMovie = movie.getIdDb();
            movie.setOriginal_language(result.get("original_language").asText());
            movie.setOriginal_title(result.get("original_title").asText());
            movie.setOverview(result.get("overview").asText());
            movie.setPopularity(result.get("popularity").asDouble());
            movie.setPoster_path(result.get("poster_path").asText());
            movie.setRelease_date(result.get("release_date").asText());
            movie.setTitle(result.get("title").asText());
            movie.setVideo(result.get("video").asBoolean());
            movie.setVote_average(result.get("vote_average").asDouble());
            movie.setVote_count(result.get("vote_count").asInt());

            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/"+ idMovie +"/credits?language=en-US"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYTc5ZDI1OWFjNWZhYTM4ZTcwNDdkYjlkMWEzYWNjYiIsInN1YiI6IjY1MzZkMDM4OTQ2MzE4MDBhZDk0MDA1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6f5J39O2-Irt0iTC4TDXsiEdCTChCARLF7yvx-72_rE")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println(response2.uri());

            ObjectMapper objectMapper2 = new ObjectMapper();
            JsonNode jsonResponse2 = objectMapper2.readTree(response2.body());

            // Accéder aux résultats
            JsonNode casts = jsonResponse2;

            JsonNode castings = jsonResponse2.get("cast");

            for (JsonNode casting : castings) {
                if (casting.get("known_for_department").asText().equals("Acting")) {
                    Cast cast = new Cast();
                    cast.setCharacter(casting.get("character").asText());
                    cast.setAdult(casting.get("adult").asBoolean());
                    cast.setGender(casting.get("gender").asInt());
                    cast.setIdDb(casting.get("id").asInt());
                    cast.setKnown_for_department(casting.get("known_for_department").asText());
                    cast.setName(casting.get("name").asText());
                    cast.setOriginal_name(casting.get("original_name").asText());
                    cast.setPopularity(casting.get("popularity").asDouble());
                    cast.setProfile_path(casting.get("profile_path").asText());
                    cast.setCast_id(casting.get("cast_id").asInt());
                    cast.setCharacter(casting.get("character").asText());
                    cast.setCredit_id(casting.get("credit_id").asText());
                    cast.setOrder(casting.get("order").asInt());
                    cast.setMovieId(casts.get("id").asInt());

                    castRepository.save(cast);
                    System.out.println(cast.getName());
                    System.out.println(cast.getMovieId());
                    System.out.println(cast.getCharacter());


                }


            }

            movieRepository.save(movie);
        }


        System.out.println(request.uri());

    }

}
