package com.example.graphqlexample.resolver.zoo.employee;

import com.example.graphqlexample.domain.zoo.Employee;
import com.example.graphqlexample.dto.EmployeeDTO;
import com.example.graphqlexample.dto.input.InputEmployee;
import com.example.graphqlexample.repository.EmployeeRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMutationResolver implements GraphQLMutationResolver {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDTO registerEmployee(InputEmployee inputEmployee){

        Employee employee = Employee.builder()
                                    .age(inputEmployee.getAge())
                                    .name(inputEmployee.getName())
                                    .employeeType(inputEmployee.getEmployeeType())
                                    .build();

        Employee save = employeeRepository.save(employee);

        return modelMapper.map(save, EmployeeDTO.class);
    }

}
