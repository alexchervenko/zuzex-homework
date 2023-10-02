package com.example.HomeResidents.service;

import com.example.HomeResidents.model.Person;
import com.example.HomeResidents.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final HomeService homeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, HomeService homeService, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.homeService = homeService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById (int id) {return personRepository.findById(id);}

    public Optional<Person> getPersonByName(String name) {
        return personRepository.getPersonByName(name);
    }

    @Transactional
    public void save(Person person) {
        person.setRole("ROLE_USER");
//        person.setHome(homeService.findById(3).get());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public void update(int id ,Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }
}


