package com.example.Back.controller;

import com.example.Back.domain.Person;
import com.example.Back.repository.PersonSearch;
import com.example.Back.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PeopleController {

    private final PersonService personService;

    @GetMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public void create(@ModelAttribute("personSearch") PersonSearch personSearch, Model model)
    {
        List<Person> people = personService.findPeople(personSearch);
        model.addAttribute("people",people);
    }

}
