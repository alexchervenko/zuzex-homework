package com.example.HomeResidents.controller;

import com.example.HomeResidents.dto.HomeDTO;
import com.example.HomeResidents.model.Home;
import com.example.HomeResidents.model.Person;
import com.example.HomeResidents.service.AuthenticatedUserService;
import com.example.HomeResidents.service.HomeService;
import com.example.HomeResidents.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homes")
public class HomeController {
    private final HomeService homeService;
    private final PersonService personService;

    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public HomeController(HomeService homeService, PersonService personService, AuthenticatedUserService authenticatedUserService) {
        this.homeService = homeService;
        this.personService = personService;
        this.authenticatedUserService = authenticatedUserService;
    }

    @GetMapping()
    public List<Home> getHomes(){
        return homeService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody Home home) {
        homeService.save(home);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody Home home) {
        homeService.delete(home.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody Home home) {
        homeService.update(home.getId(), home);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/set_resident")
    public ResponseEntity<HttpStatus> setHome(@RequestBody HomeDTO homeDTO) {
        if (authenticatedUserService.isOwner(homeDTO.home_id)){
            Optional<Person> newResident = personService.findById(homeDTO.person_id);
            Optional<Home> existingHome = homeService.findById(homeDTO.home_id);
            if (newResident.isPresent() && existingHome.isPresent()){
                newResident.get().setHome(existingHome.get());
                personService.save(newResident.get());
            }
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/delete_resident")
    public ResponseEntity<HttpStatus> deleteResident(@RequestBody HomeDTO homeDTO) {
        if (authenticatedUserService.isOwner(homeDTO.home_id)) {
            Optional<Person> personToDelete = personService.findById(homeDTO.person_id);
            if (personToDelete.isPresent()) {
                personToDelete.get().setHome(homeService.findById(1).get());
                personService.save(personToDelete.get());
            }
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/set_owner")
    public ResponseEntity<HttpStatus> setOwner(@RequestBody HomeDTO homeDTO) {
        Optional<Person> newOwner = personService.findById(homeDTO.person_id);
        Optional<Home> home = homeService.findById(homeDTO.home_id);
        if (newOwner.isPresent() && home.isPresent()){
            home.get().setOwner(newOwner.get());
            homeService.save(home.get());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
