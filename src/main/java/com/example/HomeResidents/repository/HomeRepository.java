package com.example.HomeResidents.repository;

import com.example.HomeResidents.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {

}
