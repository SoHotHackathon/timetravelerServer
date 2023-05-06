package com.example.Back.service;

import com.example.Back.controller.dto.conversationReq;
import com.example.Back.domain.Conversation;
import com.example.Back.domain.Member;
import com.example.Back.domain.Person;
import com.example.Back.repository.ConversationRepository;
import com.example.Back.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversationService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private chatGPTService chatGPTService;

    public void createConversation(conversationReq requestDto) {

        Person person = personRepository.findOne(requestDto.getPerson_id());
        Member member = new Member();

        member.setAge(requestDto.getAge());
        member.setMBTI(requestDto.getMbti());
        member.setJob(requestDto.getJob());
        member.setGender(requestDto.getGender());
        member.setConsulting(requestDto.getConsulting());

        log.info("멤버 생성",member.getId());

        Conversation conversation = new Conversation();
        conversation.setMember(member);
        conversation.setPerson(person);
        conversation.setCreatedTime(LocalDateTime.now());

        log.info("conversation 생성",conversation.getId());

        try {
            String script = chatGPTService.completeChat(requestDto.getConsulting());
            System.out.println(script);
            conversation.setScript(script);
            conversationRepository.save(conversation);
        } catch (IllegalArgumentException e) {
            log.info("Error during updating lecture");
        }
    }
}
