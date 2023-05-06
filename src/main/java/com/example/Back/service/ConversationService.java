package com.example.Back.service;

import com.example.Back.controller.dto.conversationReq;
import com.example.Back.domain.Conversation;
import com.example.Back.domain.Member;
import com.example.Back.domain.Person;
import com.example.Back.repository.ConversationRepository;
import com.example.Back.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;
    private PersonRepository personRepository;
    private chatGPTService chatGPTService;

    public void createConversation(conversationReq requestDto) {

        Member member = new Member();
        member.setAge(requestDto.getAge());
        member.setMBTI(requestDto.getMbti());
        member.setJob(requestDto.getJob());
        member.setName(requestDto.getName());
        member.setGender(requestDto.getGender());
        member.setConsulting(requestDto.getConsulting());


        Person person = personRepository.getById(requestDto.getId());


        Conversation conversation = new Conversation();
        conversation.setMember(member);
        conversation.setPerson(person);
        conversation.setCreatedTime(LocalDateTime.now());

        try {
            String script = chatGPTService.completeChat(requestDto.getConsulting());
            conversation.setScript(script);

        } catch (IllegalArgumentException e) {
            System.out.println("Error during updating lecture");
        }
        conversationRepository.save(conversation);
    }
}
