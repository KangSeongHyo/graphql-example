package com.example.graphqlexample.resolver.zoo.employee;

import com.example.graphqlexample.domain.zoo.Employee;
import com.example.graphqlexample.dto.EmployeeDTO;
import com.example.graphqlexample.repository.EmployeeRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeQueryResolver implements GraphQLQueryResolver {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDTO> employees(){
        List<Employee> employeeList = employeeRepository.findAll();

        return employeeList
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
