package com.example.projetFilRouge.repositories;

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


    public List<Tutorial> findByTitleContaining(String title) {
//        String q = "SELECT * from tutorials t join credits c on c.credits_id_csv = t.movie_id WHERE title ILIKE '%" + title + "%' OR genres ILIKE '%" + title + "%' OR crew ILIKE '%" + title + "%' ";

        String q = "SELECT * from tutorials WHERE title ILIKE '%" + title + "%'";

//        String q = "SELECT * from tutorials t join credits c on c.credits_id_csv = t.movie_id WHERE title ILIKE '%" + title + "%' OR genres ILIKE '%" + title + "%' OR crew ILIKE '%" + title + "%' ";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
//    select distinct t.title  , c.crew from tutorials t join credits c on c.credits_id_csv = t.movie_id  WHERE title = 'Braveheart'
}
