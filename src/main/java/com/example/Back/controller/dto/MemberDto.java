package com.example.Back.controller.dto;

import com.example.Back.domain.Gender;
import com.example.Back.domain.MBTI;

public class MemberDto {
    private Long id;
    private String name;
    private MBTI MBTI;
    private Gender gender;
    private int age;
    private String job;
    private String consulting;
}
