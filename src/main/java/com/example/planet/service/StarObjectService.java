package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.DiscoverySourceRepository;
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


@Service
@RequiredArgsConstructor
public class StarObjectService {

    private final StarObjectRepository starObjectRepository;

    private final DiscoverySourceService discoverySourceService;
    private final DiscoverySourceRepository discoverySourceRepository;

    public Page<StarObject> getAllStarObjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return starObjectRepository.findAll(pageable);
    }

    public StarObject getStarObjectById(Long id) {
        return starObjectRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<StarObject> saveManyStarObjects(final List<StarObject> starObject) {
        final List<StarObject> objects = starObject.stream()
                .filter(this::checkExistenceOfDiscoverySource)
                .map(this::defineTypeOfStar)
                .toList();
        return starObjectRepository.saveAll(objects);
    }

    public StarObject updateStarObject(Long id, final StarObject starObject) {
        Optional<StarObject> existingObject = starObjectRepository.findById(id);
        if (existingObject.isPresent()) {
            var object = existingObject.get();
            return checkIfUpdateStarNeeded(starObject, object);
        }
        return starObject;
    }

    private StarObject checkIfUpdateStarNeeded(final StarObject starObject, StarObject existingObject) {
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

    private StarObject defineTypeOfStar(final StarObject starObject) {
        if (starObject.getMass() > 1000L && starObject.getMass() < 10000L) {

            starObject.setObjectType(PLANET);
        } else if (starObject.getMass() > 10000L && starObject.getMass() < 100000L) {
            starObject.setObjectType(STAR);
        } else
            starObject.setObjectType(BLACK_HOLE);
        return starObject;
    }

    private boolean checkExistenceOfDiscoverySource(final StarObject starObject) {
        return discoverySourceRepository.findById(starObject.getDiscoverySourceId()).isPresent();
    }
}
