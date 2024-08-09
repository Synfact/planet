package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.StarObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.planet.model.ObjectType.*;
import static com.example.planet.model.Type.*;


@Service
@RequiredArgsConstructor
public class StarObjectService {

    private final StarObjectRepository starObjectRepository;

    public Page<StarObject> getAllStarObjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return starObjectRepository.findAll(pageable);
    }

    public Optional<StarObject> getStarObjectById(Long id) {
        return starObjectRepository.findById(id);
    }

    public List<StarObject> saveManyStarObjects(final List<StarObject> starObject) {
        for (StarObject o : starObject) {
            defineTypeOfStar(o);
        }
        return starObjectRepository.saveAll(starObject);
    }

    public StarObject saveOneStarObject(final StarObject starObject) {
        defineTypeOfStar(starObject);
        return starObjectRepository.save(starObject);
    }

    public void defineTypeOfStar(final StarObject starObject) {
        if (starObject.getMass() > 1000L && starObject.getMass() < 10000L) {

            starObject.setObjectType(PLANET);
        } else if (starObject.getMass() > 10000L && starObject.getMass() < 100000L) {
            starObject.setObjectType(STAR);
        } else
            starObject.setType(BLACK_HOLE);
    }
}
