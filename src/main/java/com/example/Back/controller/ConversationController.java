package com.example.Back.controller;

import com.example.Back.controller.dto.conversationReq;
import com.example.Back.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConversation(@RequestBody conversationReq requestDto) {
        conversationService.createConversation(requestDto);

    }


}
