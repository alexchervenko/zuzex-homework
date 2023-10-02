package com.example.HomeResidents.service;

import com.example.HomeResidents.repository.HomeRepository;
import com.example.HomeResidents.repository.PersonRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticatedUserService {
    private final HomeRepository homeRepository;

    public AuthenticatedUserService(PersonRepository personRepository, HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public boolean isOwner(int id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String ownerName = homeRepository.findById(id).get().getOwner().getName();
        return Objects.equals(ownerName, name);

    }
}
