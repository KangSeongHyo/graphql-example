package com.example.graphqlexample.domain;

import com.example.graphqlexample.constant.MemberType;
import com.example.graphqlexample.vo.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

}
