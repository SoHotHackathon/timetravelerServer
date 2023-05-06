package com.example.Back.controller;

import com.example.Back.domain.Person;
import com.example.Back.repository.PersonSearch;
import com.example.Back.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PersonService personService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPeopleList()
    {
        List<Person> people = personService.findPeople();
        log.info("people", people);
        return ResponseEntity.ok().body(people);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<?> getPeople(@PathVariable("category_id") Long category_id)
    {
        List<Person> people = personService.findByCategoryId(category_id);
        log.info("people", people);
        return ResponseEntity.ok().body(people);
    }

}
