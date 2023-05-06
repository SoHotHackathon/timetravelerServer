package com.example.Back.controller.dto;

import com.example.Back.domain.Gender;
import com.example.Back.domain.MBTI;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class conversationReq {
	private Long person_id;
	private Long member_id;

}
