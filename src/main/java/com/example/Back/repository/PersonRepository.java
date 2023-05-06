package com.example.Back.repository;

import com.example.Back.domain.Conversation;
import com.example.Back.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
