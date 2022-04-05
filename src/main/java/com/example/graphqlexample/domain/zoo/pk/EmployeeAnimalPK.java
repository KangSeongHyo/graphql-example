package com.example.graphqlexample.domain.zoo.pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class EmployeeAnimalPK implements Serializable {

    private Long employee;

    private Long animal;

}
