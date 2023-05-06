package com.example.Back.controller.dto;

import com.example.Back.domain.Gender;
import com.example.Back.domain.MBTI;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class conversationReq {


	private Long person_id;
	private String name;
	private String field;


	private int age;
	private Gender gender;
	private String job;
	private MBTI mbti;

	private String consulting;
	private String gptScript;

	// getters, setters, constructors
}
