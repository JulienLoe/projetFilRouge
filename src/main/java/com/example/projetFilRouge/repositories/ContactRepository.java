package com.example.projetFilRouge.repositories;

import com.example.projetFilRouge.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findAllByFirstNameStartingWith (String value);
}

