package com.example.Back.controller;

import com.example.Back.controller.dto.conversationReq;
import com.example.Back.controller.dto.memberReq;
import com.example.Back.domain.Gender;
import com.example.Back.domain.MBTI;
import com.example.Back.service.ConversationService;
import com.example.Back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/new")
    public ResponseEntity<?> createMember(@RequestBody memberReq memberReq) {
        Long memberId = memberService.createMember(memberReq);
        return ResponseEntity.ok().body(memberId);
    }
}
