package com.example.Back.domain;

import javax.persistence.*;

@Entity
public class Member
{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;
    private String mbti;
    private String job;

    @Lob
    private String consulting;
}
