package com.example.planet.controller;

import com.example.planet.model.StarObject;
import com.example.planet.service.StarObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StarObjectController {

   private final StarObjectService starObjectService;

    @GetMapping("/object")
    private List<StarObject> getAllObjects(){
        return starObjectService.getAllObjects();
    }

    @GetMapping("/object/{id}")
    private StarObject getObjects(@PathVariable Long id){
        return starObjectService.getObjectById(id);
    }

    @PostMapping("/object")
    private StarObject saveOne(@RequestBody StarObject starObject){
        return starObjectService.saveOne(starObject);
    }

    @PostMapping("/objects")
    private List<StarObject> saveMany(@RequestBody List<StarObject> starObject){
        return starObjectService.saveMany(starObject);
    }

}
