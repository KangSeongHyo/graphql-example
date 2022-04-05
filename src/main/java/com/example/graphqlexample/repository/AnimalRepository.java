package com.example.graphqlexample.repository;


import com.example.graphqlexample.domain.zoo.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Long> {

}
