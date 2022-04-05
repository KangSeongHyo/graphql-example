package com.example.graphqlexample.domain.zoo;

import com.example.graphqlexample.constant.AnimalType;
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
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    private String name;

    private String kind;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY)
    private List<EmployeeAnimal> employeeAnimalList;
}
