package com.example.Back.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityReference(alwaysAsId = true)
public class Member
{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "member")
    @JsonBackReference
    private List<Conversation> conversationList = new ArrayList<>();

    private String name;
    @Enumerated(EnumType.STRING)
    private MBTI MBTI;

    @Enumerated(EnumType.STRING)
    private Gender Gender;

    private int age;

    private String job;

    @Lob
    private String consulting;
}