package com.example.graphqlexample.repository;

import com.example.graphqlexample.domain.zoo.Employee;
import com.example.graphqlexample.domain.zoo.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    Place findByKind(String kind);
}
