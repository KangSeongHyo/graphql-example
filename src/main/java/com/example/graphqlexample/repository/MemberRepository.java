package com.example.graphqlexample.repository;

import com.example.graphqlexample.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
