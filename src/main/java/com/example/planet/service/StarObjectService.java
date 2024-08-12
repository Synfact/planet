package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.StarObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.planet.model.ObjectType.*;
import static com.example.planet.model.Type.*;


@Service
@RequiredArgsConstructor
public class StarObjectService {

    private final StarObjectRepository starObjectRepository;

    private final DiscoverySourceService discoverySourceService;

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
        if (checkExistenceOfDiscoverySource(starObject)) {
            defineTypeOfStar(starObject);
            return starObjectRepository.save(starObject);
        }
        return starObject;
    }

    public StarObject updateStarObject(Long id, final StarObject starObject) {
        Optional<StarObject> existingObject = starObjectRepository.findById(id);
        if (existingObject.isPresent()) {
            var object = existingObject.get();
            return checkUpdate(starObject, object);
        }
        return starObject;
    }

    public boolean checkExistenceOfDiscoverySource(final StarObject starObject) {
        return discoverySourceService.getDiscoverySourceById(starObject.getDiscoverySourceId()).isPresent();
    }

    public StarObject checkUpdate(final StarObject starObject, StarObject existingObject) {
        if (Objects.nonNull(starObject.getName())) {
            existingObject.setName(starObject.getName());
        }
        if (Objects.nonNull(starObject.getEquatorialDiameter())) {
            existingObject.setEquatorialDiameter(starObject.getEquatorialDiameter());
        }
        if (Objects.nonNull(starObject.getDiscoverySourceId())) {
            existingObject.setDiscoverySourceId(starObject.getDiscoverySourceId());
        }
        if (Objects.nonNull(starObject.getDiscoveryDate())) {
            existingObject.setDiscoveryDate(starObject.getDiscoveryDate());
        }
        if (Objects.nonNull(starObject.getMass())) {
            existingObject.setMass(starObject.getMass());
            defineTypeOfStar(existingObject);
        }
        return starObjectRepository.save(existingObject);
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
