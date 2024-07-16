package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.StarObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StarObjectService {

    private final StarObjectRepository starObjectRepository;

    public List<StarObject> getAllObjects() {
        return starObjectRepository.findAll();
    }

    public StarObject getObjectById(Long id) {++
        Iterable<Long> ids = Collections.singleton(id);
        return starObjectRepository.findAllById(ids);
    }

    public List<StarObject> saveMany(final List<StarObject> starObject) {
        return starObjectRepository.saveAll(starObject);}

    public StarObject saveOne(final StarObject starObject) {
        return starObjectRepository.save(starObject);}
}
