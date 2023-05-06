package com.example.Back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Person
{
    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String photoUrl;

    private LocalDate birthDate;
    private LocalDate deathDate;

}
