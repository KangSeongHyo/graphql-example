package com.example.graphqlexample.resolver;

import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@GraphQLTest
class MemberResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void echoMember() throws IOException {
        graphQLTestTemplate.postForResource("echoMember");
    }
}