package com.example.graphqlexample.resolver;

import com.example.graphqlexample.constant.MemberType;
import com.example.graphqlexample.domain.Member;
import com.example.graphqlexample.dto.MemberDTO;
import com.example.graphqlexample.repository.MemberRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberMutationResolver implements GraphQLMutationResolver {

    private final MemberRepository memberRepository;

    public long createMember(MemberDTO memberDTO){
        Member member = Member.builder()
                .name(memberDTO.getName())
                .age(memberDTO.getAge())
                .memberType(memberDTO.getMemberType())
                .build();
        Member save = memberRepository.save(member);
        return save.getId();
    }
}
