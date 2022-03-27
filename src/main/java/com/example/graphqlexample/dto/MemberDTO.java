package com.example.graphqlexample.dto;

import com.example.graphqlexample.constant.MemberType;
import com.example.graphqlexample.vo.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberDTO {
    private Long id;
    private String name;
    private Integer age;
    private MemberType memberType;

    @Setter
    private Manager manager;
}
