package com.example.Back.service;

import com.example.Back.domain.Person;
import com.example.Back.repository.PersonRepository;
import com.example.Back.repository.PersonSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService
{
    private final PersonRepository personRepository;

    //검색
    public List<Person> findPeople()
    {
        return personRepository.findAll();
    }

    public List<Person> findByCategoryId(Long category_id)
    {
        return personRepository.findByCategoryId(category_id);
    }
}
