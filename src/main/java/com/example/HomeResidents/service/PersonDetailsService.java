package com.example.HomeResidents.service;

import com.example.HomeResidents.model.PersonDetails;
import com.example.HomeResidents.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PersonDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository
                .getPersonByName(username)
                .map(PersonDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с таким именем не существует"));
    }
}

