package com.example.projetFilRouge.helper;


import com.example.projetFilRouge.entities.Cast;
import com.example.projetFilRouge.entities.Episode;
import com.example.projetFilRouge.entities.Season;
import com.example.projetFilRouge.entities.Serie;
import com.example.projetFilRouge.repositories.CastRepository;
import com.example.projetFilRouge.repositories.EpisodeRepository;
import com.example.projetFilRouge.repositories.SeasonRepository;
import com.example.projetFilRouge.repositories.SerieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SerieHelper {

    private final SerieRepository serieRepository;
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;
    private final CastRepository castRepository;



    public void getAllSeries(int number) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/tv?include_adult=false&include_null_first_air_dates=false&language=fr&sort_by=popularity.desc&page=" + number))
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

        for (JsonNode result : results) {

            Serie serie = new Serie();

            serie.setBackdrop_path(result.get("backdrop_path").asText());
            serie.setFirst_air_date(result.get("first_air_date").asText());
            serie.setGenre_ids(result.get("genre_ids").toString());
            serie.setIdDb(result.get("id").asInt());
            serie.setName(result.get("name").asText());
            serie.setOrigin_country(result.get("origin_country").toString());
            serie.setOriginal_language(result.get("original_language").asText());
            serie.setOriginal_name(result.get("original_name").asText());
            serie.setOverview(result.get("overview").asText());
            serie.setPopularity(result.get("popularity").asDouble());
            serie.setPoster_path(result.get("poster_path").asText());
            serie.setVote_average(result.get("vote_average").asDouble());
            serie.setVote_count(result.get("vote_count").asInt());
            serie.setPage(number);

            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/tv/" + serie.getIdDb() + "?language=fr"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYTc5ZDI1OWFjNWZhYTM4ZTcwNDdkYjlkMWEzYWNjYiIsInN1YiI6IjY1MzZkMDM4OTQ2MzE4MDBhZDk0MDA1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6f5J39O2-Irt0iTC4TDXsiEdCTChCARLF7yvx-72_rE")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println(response2.body());

            ObjectMapper objectMapper2 = new ObjectMapper();
            JsonNode jsonResponse2 = objectMapper2.readTree(response2.body());

            // Accéder aux résultats
            JsonNode detail = jsonResponse2;
            JsonNode seasons = jsonResponse2.get("seasons");

            serie.setNbr_seasons(detail.get("number_of_seasons").asInt());
            serie.setNbr_episodes(detail.get("number_of_episodes").asInt());

            if(serie.getNbr_seasons() < 25) {
                serieRepository.save(serie);

                for (JsonNode s : seasons) {

                    Season season = new Season();

//                season.setCreated_by(s.get("created_by").toString());
                    season.setMovie_Id(detail.get("id").asInt());
                    season.setAir_date(s.get("air_date").asText());
                    season.setNbr_episodes(s.get("episode_count").asInt());
                    season.setName(s.get("name").asText());
                    season.setPoster_path(s.get("poster_path").asText());
                    season.setVote_average(s.get("vote_average").asDouble());

                    seasonRepository.save(season);
                }

                HttpRequest request4 = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.themoviedb.org/3/tv/"+ serie.getIdDb() +"/credits?language=fr"))
                        .header("accept", "application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYTc5ZDI1OWFjNWZhYTM4ZTcwNDdkYjlkMWEzYWNjYiIsInN1YiI6IjY1MzZkMDM4OTQ2MzE4MDBhZDk0MDA1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6f5J39O2-Irt0iTC4TDXsiEdCTChCARLF7yvx-72_rE")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response4 = HttpClient.newHttpClient().send(request4, HttpResponse.BodyHandlers.ofString());
                System.out.println(response4.body());

                ObjectMapper objectMapper4 = new ObjectMapper();
                JsonNode jsonResponse4 = objectMapper4.readTree(response4.body());

                JsonNode casts = jsonResponse4;
                JsonNode castings = jsonResponse4.get("cast");

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
                        cast.setCharacter(casting.get("character").asText());
                        cast.setCredit_id(casting.get("credit_id").asText());
                        cast.setOrder(casting.get("order").asInt());
                        cast.setMovieId(casts.get("id").asInt());
                        cast.setOfSerie(true);
                        if(casting.get("cast_id") != null) {
                            cast.setCast_id(casting.get("cast_id").asInt());
                        }
//                    System.out.println(casting.get("cast_id"));

                        castRepository.save(cast);


                    }
                }
            }
        }
    }

    public void requestEpisode(int serieId, int numberSeason) throws IOException, InterruptedException {
        HttpRequest request3 = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/tv/" + serieId + "/season/" + numberSeason + "?language=fr"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYTc5ZDI1OWFjNWZhYTM4ZTcwNDdkYjlkMWEzYWNjYiIsInN1YiI6IjY1MzZkMDM4OTQ2MzE4MDBhZDk0MDA1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6f5J39O2-Irt0iTC4TDXsiEdCTChCARLF7yvx-72_rE")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response3 = HttpClient.newHttpClient().send(request3, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper3 = new ObjectMapper();
        JsonNode jsonResponse3 = objectMapper3.readTree(response3.body());

        // Accéder aux résultats
        JsonNode episodes = jsonResponse3.get("episodes");

        if (episodes != null) {
            for (JsonNode ep : episodes) {

                Episode episode = new Episode();
                episode.setAir_date(ep.get("air_date").asText());
                episode.setEpisode_number(ep.get("episode_number").asInt());
                episode.setEpisode_type(ep.get("episode_type").asText());
                episode.setIdDb(ep.get("id").asInt());
                episode.setName(ep.get("name").asText());
                episode.setOverview(ep.get("overview").asText());
                episode.setProduction_code(ep.get("production_code").asText());
                episode.setRuntime(ep.get("runtime").asInt());
                episode.setSeason_number(ep.get("season_number").asInt());
                episode.setShow_id(ep.get("show_id").asInt());
                episode.setStill_path(ep.get("still_path").asText());
                episode.setVote_average(ep.get("vote_average").asDouble());
                episode.setVote_count(ep.get("vote_count").asInt());

                episodeRepository.save(episode);

            }
        }
    }


    public void getDetailSeries(int number, int numberList) throws IOException, InterruptedException {

        List<Serie> series = serieRepository.findAll();
        List<Serie> newSeries = new ArrayList<>();
        for(Serie serie : series) {
            if(serie.getPage() == number) {
                newSeries.add(serie);
            }
        }

        List<Serie> sublist1 = newSeries.subList(0, 5);
        List<Serie> sublist2 = newSeries.subList(5, 10);
        List<Serie> sublist3 = newSeries.subList(10, 15);
        List<Serie> sublist4 = newSeries.subList(15, newSeries.size());

        switch(numberList){

            case 1:
                for (Serie s : sublist1) {
                    if (s.getNbr_seasons() < 25) {
                        for (int i = 1; i <= s.getNbr_seasons(); i++) {

                            requestEpisode(s.getIdDb(), i);

                        }
                    }
                }
                break;

            case 2:
                for (Serie s2 : sublist2) {
                    if (s2.getNbr_seasons() < 25) {
                        for (int i = 1; i <= s2.getNbr_seasons(); i++) {

                            requestEpisode(s2.getIdDb(), i);

                        }

                    }
                }
                break;

            case 3:
                for (Serie s3 : sublist3) {
                    if (s3.getNbr_seasons() < 25) {
                        for (int i = 1; i <= s3.getNbr_seasons(); i++) {

                            requestEpisode(s3.getIdDb(), i);

                        }

                    }
                }
                break;
            case 4:
                for (Serie s4 : sublist4) {
                    if (s4.getNbr_seasons() < 25) {
                        for (int i = 1; i <= s4.getNbr_seasons(); i++) {

                            requestEpisode(s4.getIdDb(), i);

                        }

                    }
                }
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }

    }

}
