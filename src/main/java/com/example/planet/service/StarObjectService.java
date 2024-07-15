package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.StarObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StarObjectService {

    private final StarObjectRepository starObjectRepository;

    public List<StarObject> getAllObjects() {
        return starObjectRepository.findAll();
    }

    public List<StarObject> saveMany(final List<StarObject> starObject) {
        return starObjectRepository.saveAll(starObject);}

    public StarObject saveOne(final StarObject starObject) {
        return starObjectRepository.save(starObject);}
}
