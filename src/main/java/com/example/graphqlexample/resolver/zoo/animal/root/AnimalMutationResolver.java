package com.example.graphqlexample.resolver.zoo.animal.root;

import com.example.graphqlexample.domain.zoo.Animal;
import com.example.graphqlexample.domain.zoo.Employee;
import com.example.graphqlexample.domain.zoo.EmployeeAnimal;
import com.example.graphqlexample.domain.zoo.pk.EmployeeAnimalPK;
import com.example.graphqlexample.dto.AnimalDTO;
import com.example.graphqlexample.dto.input.InputAnimal;
import com.example.graphqlexample.repository.AnimalRepository;
import com.example.graphqlexample.repository.EmployeeAnimalRepository;
import com.example.graphqlexample.repository.EmployeeRepository;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AnimalMutationResolver implements GraphQLMutationResolver {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAnimalRepository employeeAnimalRepository;

    public AnimalDTO registerAnimal(InputAnimal inputAnimal){

        Animal animal = Animal.builder()
                             .name(inputAnimal.getName())
                             .age(inputAnimal.getAge())
                             .kind(inputAnimal.getKind())
                             .animalType(inputAnimal.getAnimalType())
                             .build();

        Animal save = animalRepository.save(animal);

        return modelMapper.map(save,AnimalDTO.class);
    }

    @Transactional
    public DataFetcherResult<AnimalDTO> assignAnimalToZookeeper(Long animalId, Long employeeId){

        Animal animal = animalRepository.findById(animalId).orElseThrow();
        Employee employee = employeeRepository.getById(employeeId);

        EmployeeAnimalPK pk = EmployeeAnimalPK.builder().animal(animalId).employee(employeeId).build();

        if(employeeAnimalRepository.existsById(pk)){
            return DataFetcherResult.<AnimalDTO>newResult()
                    .error(new GenericGraphQLError(" 이미 배정 되어있습니다."))
                    .build();
        }

        EmployeeAnimal employeeAnimal = EmployeeAnimal.builder()
                                                        .animal(animal)
                                                        .employee(employee)
                                                        .build();

        employeeAnimalRepository.save(employeeAnimal);

        return DataFetcherResult.<AnimalDTO>newResult()
                .data(modelMapper.map(animal,AnimalDTO.class))
                .build();
    }
}
