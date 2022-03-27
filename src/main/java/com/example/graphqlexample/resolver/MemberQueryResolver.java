package com.example.graphqlexample.resolver;

import com.example.graphqlexample.domain.Member;
import com.example.graphqlexample.dto.MemberDTO;
import com.example.graphqlexample.repository.MemberRepository;
import com.example.graphqlexample.vo.SilverManager;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberQueryResolver implements GraphQLQueryResolver {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public List<MemberDTO> getMemberList(){
        List<Member> memberList = memberRepository.findAll();
        return memberList
                .stream().map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    public MemberDTO getMember(Long id){
        Member member = memberRepository.findById(id).orElse(null);
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
        memberDTO.setManager(SilverManager.builder().name("kim").score(5).build());
        return memberDTO;
    }

}
