package com.example.graphqlexample.resolver;

import com.example.graphqlexample.vo.Member;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class MemberResolver implements GraphQLQueryResolver {

    public Member echoMember(Integer id, String name){
        return Member.builder()
                .id(id)
                .name(name)
                .build();
    }
}
