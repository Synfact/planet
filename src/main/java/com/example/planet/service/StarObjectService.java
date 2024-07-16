package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.StarObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StarObjectService {

    private final StarObjectRepository starObjectRepository;

    public List<StarObject> getAllObjects() {
        return starObjectRepository.findAll();
    }

    public Optional<StarObject> getObjectById(Long id) {
        return starObjectRepository.findById(id);
    }

    public List<StarObject> saveMany(final List<StarObject> starObject) {
        return starObjectRepository.saveAll(starObject);}

    public StarObject saveOne(final StarObject starObject) {
        return starObjectRepository.save(starObject);}
}
