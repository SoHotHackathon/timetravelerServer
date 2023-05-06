package com.example.Back.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Conversation
{
    @Id
    @GeneratedValue
    @Column(name = "conversation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Lob
    private String script;

    private LocalDateTime createdTime;
}