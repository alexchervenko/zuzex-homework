package com.example.HomeResidents.controller;

import com.example.HomeResidents.dto.PersonDTO;
import com.example.HomeResidents.model.Person;
import com.example.HomeResidents.service.HomeService;
import com.example.HomeResidents.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
public class PersonController {
    private final PersonService personService;
    private final HomeService homeService;

    @Autowired
    public PersonController(PersonService personService, HomeService homeService) {
        this.personService = personService;
        this.homeService = homeService;
    }

    @GetMapping()
    public List<Person> getResidents() {
        return personService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody PersonDTO personDTO) {
        Person newPerson = new Person(personDTO.getUsername(), personDTO.getPassword());
        personService.save(newPerson);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody Person person) {
        personService.delete(person.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody Person person) {
        personService.update(person.getId(), person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
