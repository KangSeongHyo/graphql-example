package com.example.graphqlexample.repository;

import com.example.graphqlexample.domain.zoo.EmployeeAnimal;
import com.example.graphqlexample.domain.zoo.pk.EmployeeAnimalPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAnimalRepository extends JpaRepository<EmployeeAnimal, EmployeeAnimalPK> {

}
