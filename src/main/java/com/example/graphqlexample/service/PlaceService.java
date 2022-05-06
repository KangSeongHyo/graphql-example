package com.example.graphqlexample.service;

import com.example.graphqlexample.domain.zoo.Place;
import com.example.graphqlexample.repository.PlaceRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.Try;
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

    public List<Try<String>> getLocationListWithTry(List<String> kindList){
        log.info("Service 실행");
        List<Place> placeList = placeRepository.findAll();

        List<Try<String>> locationListTry = new ArrayList<>();
        for (String kind : kindList){
            Try<String> location = Try.tryCall(() -> getLocation(placeList, kind));
            locationListTry.add(location);
        }
        return locationListTry;
    }

    private String getLocation(List<Place> placeList, String kind) {
        log.info("Try 실행");
        if("개".equals(kind)){
            throw new IllegalArgumentException("[개]일 경우 IllegalArgumentException");
        }
        Map<String, String> placeMap = placeList.stream()
                .collect(Collectors.toMap(Place::getKind, Place::getPlaceName));
        String location = placeMap.get(kind);
        return location;
    }

    public Map<String, String> getLocationMap(Set<String> kindSet){
        log.info("Service 실행 (Map)");
        List<Place> placeList = placeRepository.findAll();

        Map<String, String> placeMap = placeList.stream()
                                                .filter(place -> kindSet.contains(place.getKind()))
                                                .collect(Collectors.toMap(Place::getKind, Place::getPlaceName));
        return placeMap;
    }

}
