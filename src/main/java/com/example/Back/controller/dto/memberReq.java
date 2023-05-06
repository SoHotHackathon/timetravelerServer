package com.example.Back.controller.dto;

import com.example.Back.domain.Gender;
import com.example.Back.domain.MBTI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class memberReq {
    private String name;
    private int age;
    private Gender gender;
    private String job;
    private MBTI mbti;
    private String consulting;
}
