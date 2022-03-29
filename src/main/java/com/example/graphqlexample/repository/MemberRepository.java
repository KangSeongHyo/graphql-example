package com.example.graphqlexample.repository;

import com.example.graphqlexample.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m.name from Member m where m.id=:name")
    Member getMemberType(@Param(value = "name") String name);
}
