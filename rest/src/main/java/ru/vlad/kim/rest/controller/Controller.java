package ru.vlad.kim.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.vlad.kim.rest.service.PersonService;

import java.io.IOException;

@RestController
public class Controller {
    private PersonService personService;

    @Autowired
    public Controller(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public String createPerson(@RequestBody String person) throws IOException {
        return personService.savePerson(person);
    }
}
