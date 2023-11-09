package com.example.projetFilRouge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "credits")
public class Credits {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int Id;

    @Column(length = 100000, name = "casting")
    public String cast;

    @Column(length = 100000)
    public String crew;

    @Column(name = "credits_id_csv")

    public int id;

//    @ElementCollection
//    public List<Object> cast;
//
//    @ElementCollection
//    public List<Object> crew;
    @OneToOne(mappedBy = "credits", cascade = CascadeType.ALL)
    public Tutorial tutorial;



    public Credits(String cast, String crew, int id) {
//        this.cast = new ArrayList<>();
//     this.crew = new ArrayList<>();
        this.cast = cast;
        this.crew = crew;
        this.id=id;

    }

    public Credits(){

    }

}
