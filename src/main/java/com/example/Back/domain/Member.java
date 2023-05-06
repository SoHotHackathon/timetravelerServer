package com.example.Back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Member
{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

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
