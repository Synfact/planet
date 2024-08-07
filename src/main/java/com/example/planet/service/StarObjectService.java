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
        return starObjectRepository.saveAll(starObject);}

    public StarObject saveOneStarObject(final StarObject starObject) {
        return starObjectRepository.save(starObject);}
}
