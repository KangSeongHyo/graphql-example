package com.example.graphqlexample.domain.zoo;

import com.example.graphqlexample.constant.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String name;

    private Integer age;

    private EmployeeType employeeType;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeAnimal> employeeAnimalList;

}
