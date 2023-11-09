package com.example.projetFilRouge.repositories;

import com.example.projetFilRouge.entities.*;
import com.example.projetFilRouge.models.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;



//    public List<Tutorial> findAll() {
//        return jdbcTemplate.query("SELECT * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
//    }


    public List<Movie> findByTitleContaining(String title) {
        String q = "SELECT * from movie WHERE title ILIKE '%" + title + "%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public List<Movie> findAllMovies() {
        String q = "SELECT * from movie";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public List<Cast> findAllCast() {
        String q = "SELECT * from cast";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Cast.class));
    }

    public List<Episode> findAllEpisode() {
        String q = "SELECT * from episode";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Episode.class));
    }

    public List<Serie> findAllSerie() {
        String q = "SELECT * from serie";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Serie.class));
    }

    public List<Season> findAllSeason() {
        String q = "SELECT * from seasons";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Season.class));
    }

//    public List<Category> findAllCategory() {
//        String q = "SELECT * from category";
//        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Category.class));
//    }

}
