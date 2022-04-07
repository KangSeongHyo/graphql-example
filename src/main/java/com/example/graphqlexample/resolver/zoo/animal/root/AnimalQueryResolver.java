package com.example.graphqlexample.resolver.zoo.animal.root;

import com.example.graphqlexample.domain.zoo.Animal;
import com.example.graphqlexample.dto.AnimalDTO;
import com.example.graphqlexample.repository.AnimalRepository;
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
public class AnimalQueryResolver implements GraphQLQueryResolver {

    private final AnimalRepository animalRepository;


    private final ModelMapper modelMapper;

    public List<AnimalDTO> animals(){

        List<Animal> animalList = animalRepository.findAll();
        return animalList.stream()
                         .map(animal -> modelMapper.map(animal, AnimalDTO.class))
                         .collect(Collectors.toList());
    }

    public AnimalDTO animal(Long id){
        Animal animal = animalRepository.findById(id).orElseThrow();

        return modelMapper.map(animal, AnimalDTO.class);

    }

}
