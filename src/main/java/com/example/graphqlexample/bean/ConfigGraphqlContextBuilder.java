package com.example.graphqlexample.bean;

import com.example.graphqlexample.service.PlaceService;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
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

        DataLoader<String, String> locationDataLoader = DataLoaderFactory
                .newDataLoader(kindList -> CompletableFuture.supplyAsync(() -> placeService.getLocationList(kindList)));

        return DataLoaderRegistry.newRegistry()
                .register("locationDataLoader",locationDataLoader)
                .build();
    }
}
