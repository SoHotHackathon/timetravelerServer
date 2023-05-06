package com.example.Back.controller;

import com.example.Back.domain.Conversation;
import com.example.Back.domain.Person;
import com.example.Back.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController
{
    private final ConversationService conversationService;

    @GetMapping("/{member_id}")
    public ResponseEntity<?> getConversationList()
    {
        List<Conversation> convs = conversationService.findAll();
        return ResponseEntity.ok().body(convs);
    }

}
