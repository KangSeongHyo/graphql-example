package com.example.graphqlexample.bean;

import com.example.graphqlexample.domain.zoo.Animal;
import com.example.graphqlexample.service.PlaceService;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoader;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.BatchLoaderWithContext;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.dataloader.Try;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class ConfigGraphqlContextBuilder implements GraphQLServletContextBuilder {

    private final PlaceService placeService;


    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext()
                .with(dataLoaderRegistry())
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext();
    }

    private DataLoaderRegistry dataLoaderRegistry(){

        BatchLoader<String,String> locationBatchLoader = new BatchLoader<String, String>() {
            @Override
            public CompletionStage<List<String>> load(List<String> kindList) {
                return CompletableFuture.supplyAsync(() -> placeService.getLocationList(kindList));
            }
        };

        BatchLoader<String, Try<String>> locationBatchLoaderTry = new BatchLoader<String, Try<String>>() {
            @Override
            public CompletionStage<List<Try<String>>> load(List<String> keys) {

                return CompletableFuture.supplyAsync(()->placeService.getLocationListWithTry(keys));
            }
        };

        MappedBatchLoader<String,String> mappedBatchLoader = new MappedBatchLoader<String, String>() {
            @Override
            public CompletionStage<Map<String, String>> load(Set<String> keys) {
                return CompletableFuture.supplyAsync(()->placeService.getLocationMap(keys));
            }
        };

        DataLoader<String, String> locationDataLoader = DataLoaderFactory
                .newDataLoader(locationBatchLoader);


        DataLoader<String, String> locationDataLoaderMap = DataLoaderFactory.newMappedDataLoader(mappedBatchLoader,DataLoaderOptions.newOptions().setCachingEnabled(false));
        DataLoader<String, String> locationDataLoaderTry = DataLoaderFactory.newDataLoaderWithTry(locationBatchLoaderTry);
        return DataLoaderRegistry.newRegistry()
                .register("locationDataLoader",locationDataLoader)
                .register("locationDataLoaderMap",locationDataLoaderMap)
                .register("locationDataLoaderTry",locationDataLoaderTry)
                .build();
    }
}
