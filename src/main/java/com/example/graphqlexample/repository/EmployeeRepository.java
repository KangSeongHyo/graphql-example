package com.example.graphqlexample.repository;

import com.example.graphqlexample.domain.zoo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
