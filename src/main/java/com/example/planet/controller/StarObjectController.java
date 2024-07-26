package com.example.planet.controller;

import com.example.planet.model.StarObject;
import com.example.planet.service.StarObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StarObjectController {

   private final StarObjectService starObjectService;

    @GetMapping("/object")
    public List<StarObject> getAllStarObjects(){
        return starObjectService.getAllStarObjects();
    }

    @GetMapping("/object/{id}")
    public Optional<StarObject> getStarObjectById(@PathVariable Long id){
        return starObjectService.getStarObjectById(id);
    }

    @PostMapping("/object")
    public StarObject addNewStarObject(@RequestBody StarObject starObject){
        return starObjectService.saveOneStarObject(starObject);
    }

    @PostMapping("/objects")
    public List<StarObject> addManyStarObjects(@RequestBody List<StarObject> starObject){
        return starObjectService.saveManyStarObjects(starObject);
    }

}
