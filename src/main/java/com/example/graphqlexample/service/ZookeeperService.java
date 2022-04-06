package com.example.graphqlexample.service;

import com.example.graphqlexample.domain.zoo.Animal;
import com.example.graphqlexample.domain.zoo.Employee;
import com.example.graphqlexample.domain.zoo.EmployeeAnimal;
import com.example.graphqlexample.dto.AnimalDTO;
import com.example.graphqlexample.dto.EmployeeDTO;
import com.example.graphqlexample.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ZookeeperService {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<EmployeeDTO> getEmployeeDTO(AnimalDTO animalDTO){

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Animal animal = animalRepository.findById(animalDTO.getId()).orElseThrow();

        List<EmployeeAnimal> employeeAnimalList = animal.getEmployeeAnimalList();
        List<Employee> employeeList = employeeAnimalList.stream()
                .map(EmployeeAnimal::getEmployee)
                .collect(Collectors.toList());

        return employeeList
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
