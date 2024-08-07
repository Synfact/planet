package com.example.planet.controller;

import com.example.planet.model.StarObject;
import com.example.planet.repository.StarObjectRepository;
import com.example.planet.service.StarObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@RestController
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class StarObjectController {

    private final StarObjectService starObjectService;

    private final StarObjectRepository starObjectRepository;

    @GetMapping("/object")
    public ResponseEntity<Page<StarObject>> getAllStarObjects(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<StarObject> starObjects = starObjectService.getAllStarObjects(page, size);
        return new ResponseEntity<>(starObjects, HttpStatus.OK);
    }

    @GetMapping("/object/{id}")
    public Optional<StarObject> getStarObjectById(@PathVariable Long id) {
        return starObjectService.getStarObjectById(id);
    }

    @PostMapping("/object")
    public StarObject addNewStarObject(@RequestBody StarObject starObject) {
        return starObjectService.saveOneStarObject(starObject);
    }

    @PostMapping("/objects")
    public List<StarObject> addManyStarObjects(@RequestBody List<StarObject> starObject) {
        return starObjectService.saveManyStarObjects(starObject);
    }

    @PutMapping("/object")
    public Optional<StarObject> updateStarObject(@PathVariable Long id, @RequestBody StarObject starObject) {
        return starObjectRepository.findById(id)
                .map(starObject1 -> {
                            starObject1.setName(starObject.getName());
                            starObject1.setEquatorialDiameter(starObject.getEquatorialDiameter());
                            starObject1.setDiscoverySourceId(starObject.getDiscoverySourceId());
                            starObject1.setDiscoveryDate(starObject.getDiscoveryDate());
                            starObject1.setMass(starObject.getMass());
                            return starObjectService.saveOneStarObject(starObject1);
                        }
                );
    }

}
