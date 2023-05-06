package com.example.Back.controller;

import com.example.Back.domain.Person;
import com.example.Back.repository.PersonSearch;
import com.example.Back.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class PeopleController {

    private final PersonService personService;

    @GetMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getPeopleList(@ModelAttribute("personSearch") PersonSearch personSearch, Model model)
    {
        List<Person> people = personService.findPeople(personSearch);
        return people;
    }



}
