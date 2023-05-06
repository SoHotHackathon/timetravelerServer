package com.example.Back.repository;

import com.example.Back.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long>{
    @Query("select c from Conversation c join fetch c.member join fetch c.member where c.member = :member_id")
    List<Conversation> findConversationByMemberId(@Param("member_id") Long member_id);

};
