package com.example.graphqlexample.dto.input;

import com.example.graphqlexample.constant.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputEmployee {

    private String name;
    private Integer age;
    private EmployeeType employeeType;

}
