package com.example.graphqlexample.resolver.zoo.animal;

import com.example.graphqlexample.dto.AnimalDTO;
import com.example.graphqlexample.dto.EmployeeDTO;
import com.example.graphqlexample.service.ZookeeperService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class ZookeeperResolver implements GraphQLResolver<AnimalDTO> {

    private final ZookeeperService zookeeperService;

    public CompletableFuture<List<EmployeeDTO>> zookeepers(AnimalDTO animalDTO){

        return CompletableFuture.supplyAsync(()-> zookeeperService.getEmployeeDTO(animalDTO));
    }

}
