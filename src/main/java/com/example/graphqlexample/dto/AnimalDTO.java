package com.example.graphqlexample.dto;

import com.example.graphqlexample.constant.AnimalType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AnimalDTO {

    private long id;
    private String name;
    private int age;
    private String kind;

    private AnimalType animalType;

    @Setter
    private List<EmployeeDTO> zookeepers;

}
