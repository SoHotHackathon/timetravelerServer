package com.example.Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category
{
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;
}
