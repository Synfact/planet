package com.example.planet.controller;

import com.example.planet.model.StarObject;
import com.example.planet.service.StarObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class StarObjectController {

    private final StarObjectService starObjectService;

    private final PagedResourcesAssembler<StarObject> assembler;

    @GetMapping("/object")
    public ResponseEntity<PagedModel<EntityModel<StarObject>>> getAllStarObjects(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        var starObjects = assembler.toModel(starObjectService.getAllStarObjects(page, size));
        return new ResponseEntity<>(starObjects, HttpStatus.OK);
    }

    @GetMapping("/object/{id}")
    public Optional<StarObject> getStarObjectById(@PathVariable Long id) {
        return starObjectService.getStarObjectById(id);
    }

    @PostMapping("/object")
    public ResponseEntity<StarObject> addNewStarObject(@RequestBody StarObject starObject) {
        return Objects.isNull(starObject.getId())
                ? new ResponseEntity<>(starObject,HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(starObject,HttpStatus.OK);
    }

    @PostMapping("/objects")
    public List<StarObject> addManyStarObjects(@RequestBody List<StarObject> starObject) {
        return starObjectService.saveManyStarObjects(starObject);
    }

    @PutMapping("/object/{id}")
    public StarObject updateStarObject(@PathVariable Long id, @RequestBody StarObject starObject) {
        return starObjectService.updateStarObject(id,starObject);
    }
}
