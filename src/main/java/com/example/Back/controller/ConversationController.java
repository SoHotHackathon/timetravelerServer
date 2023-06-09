package com.example.Back.controller;

import com.example.Back.controller.dto.conversationReq;
import com.example.Back.domain.Person;
import com.example.Back.service.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/chat")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;
    private final Logger log = LoggerFactory.getLogger(getClass());


    @PostMapping("/people")
    public ResponseEntity<?> createConversation(@RequestParam("member_id") Long memberId,
                                       @RequestParam("person_id") Long personId) {
        String script = conversationService.createConversation(memberId,personId);
        return ResponseEntity.ok().body(script);
    }



}
