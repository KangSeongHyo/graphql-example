package com.example.graphqlexample.dto.input;

import com.example.graphqlexample.constant.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputAnimal {

    private String name;
    private String kind;
    private Integer age;
    private AnimalType animalType;

}
