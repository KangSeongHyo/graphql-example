package com.example.graphqlexample.resolver.zoo.animal;

import com.example.graphqlexample.dto.AnimalDTO;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class PlaceResolver implements GraphQLResolver<AnimalDTO> {

    public CompletableFuture<String> location(AnimalDTO animalDTO, DataFetchingEnvironment environment){
        DataLoader<String, String> locationDataLoader = environment.getDataLoader("locationDataLoader");
        DataLoader<String, String> locationDataLoaderMap = environment.getDataLoader("locationDataLoaderMap");
        DataLoader<String, String> locationDataLoaderTry = environment.getDataLoader("locationDataLoaderTry");
        log.info("resovler 실행");
        return locationDataLoader.load(animalDTO.getKind());
    }

}
