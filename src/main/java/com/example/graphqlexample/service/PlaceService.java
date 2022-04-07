package com.example.graphqlexample.service;

import com.example.graphqlexample.domain.zoo.Place;
import com.example.graphqlexample.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<String> getLocationList(List<String> kindList){
        log.info("Service 실행");  
        List<Place> placeList = placeRepository.findAll();

        Map<String, String> placeMap = placeList.stream()
                                                .collect(Collectors.toMap(Place::getKind, Place::getPlaceName));

        List<String> locationList = kindList.stream()
                                            .map(placeMap::get)
                                            .collect(Collectors.toList());

        return locationList;
    }

}
