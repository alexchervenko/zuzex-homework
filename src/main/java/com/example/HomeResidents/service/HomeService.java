package com.example.HomeResidents.service;

import com.example.HomeResidents.model.Home;
import com.example.HomeResidents.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HomeService {

    private final HomeRepository homeRepository;

    @Autowired
    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<Home> findAll(){
        return homeRepository.findAll();
    }

    public Optional<Home> findById(int id){
        return homeRepository.findById(id);
    }

    @Transactional
    public void save(Home home) {
        homeRepository.save(home);
    }

    @Transactional
    public void delete(int id) {
        homeRepository.deleteById(id);
    }

    @Transactional
    public void update(int id ,Home updatedHome) {
        updatedHome.setId(id);
        homeRepository.save(updatedHome);
    }
}
