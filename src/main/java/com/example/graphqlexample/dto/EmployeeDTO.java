package com.example.graphqlexample.dto;

import com.example.graphqlexample.constant.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmployeeDTO {

    private long id;
    private String name;
    private int age;
    private EmployeeType employeeType;

}
