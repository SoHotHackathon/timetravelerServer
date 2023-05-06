package com.example.Back.controller;

import com.example.Back.domain.Person;
import com.example.Back.repository.PersonSearch;
import com.example.Back.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PeopleController {

    private final PersonService personService;

    @GetMapping("/people")
    public String create(@ModelAttribute("personSearch") PersonSearch personSearch, Model model)
    {
        List<Person> people = personService.findPeople(personSearch);
        model.addAttribute("people",people);
        return "로컬html경로";
    }

}
