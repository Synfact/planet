package com.example.planet.controller;

import com.example.planet.model.StarObject;
import com.example.planet.service.StarObjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarObjectControllerTest {

    @Mock
    StarObjectService starObjectService;

    @Mock
    PagedResourcesAssembler<StarObject> assembler;

    @InjectMocks
    StarObjectController starObjectController;

    @Test
    void shouldGetOneStarObject() {
        StarObject starObject = buildStarObject(1L);
        when(starObjectService.getStarObjectById(1L)).thenReturn(Optional.of(starObject));
        starObjectController.getStarObjectById(1L);
        assertEquals(Optional.of(starObject),starObjectController.getStarObjectById(1L),"should return object with ID 1");

    }

    @Test
    void shouldGetAllStarObjects() {
        final var starObjects = List.of(buildStarObject(2L),buildStarObject(3L),buildStarObject(4L));
        final PageImpl<StarObject> page = new PageImpl<>(starObjects);
        final PagedModel<EntityModel<StarObject>> pagedModel = PagedModel.wrap(starObjects, new PagedModel.PageMetadata(1,1,1));

        when(starObjectService.getAllStarObjects(0,10)).thenReturn(page);
        when(assembler.toModel(page)).thenReturn(pagedModel);

        final var actual = starObjectController.getAllStarObjects(0,10);

        assertEquals(3, actual.getBody().getContent().stream().toList().size(),"should return 3 objects");
        assertEquals(2L,actual.getBody().getContent().stream().toList().get(0).getContent().getId(),"1st element should have id 2");
        assertEquals(3L,actual.getBody().getContent().stream().toList().get(1).getContent().getId(),"2st element should have id 3");
        assertEquals(4L,actual.getBody().getContent().stream().toList().get(2).getContent().getId(),"3st element should have id 4");
    }

    @Test
    void shouldAddNewStarObjectObject(){
       final var starObject = buildStarObject(21L);

       when(starObjectService.saveOneStarObject(starObject)).thenReturn(starObject);

       starObjectController.addNewStarObject(starObject);
       assertEquals(starObject, starObjectController.addNewStarObject(starObject),"should return object with ID 21");
    }

    @Test
    void shouldaddManyStarObjects(){
        final var starObjects = List.of(buildStarObject(2L),buildStarObject(3L),buildStarObject(4L));

        when(starObjectService.saveManyStarObjects(starObjects)).thenReturn(starObjects);
        starObjectController.addManyStarObjects(starObjects);

        assertEquals(3,starObjects.size(),"should return 3 objects");
    }

    @Test
    void shouldUpdateStarObject(){
        final var starObject = buildStarObject(21L);
        final var updatedStarObject = new StarObject();
        updatedStarObject.setId(5L);
        updatedStarObject.setName("updatedName");
        updatedStarObject.setDiscoveryDate(Date.valueOf("2020-01-01"));
        updatedStarObject.setEquatorialDiameter(2L);

        when(starObjectService.getStarObjectById(21L)).thenReturn(Optional.of(starObject));
        when(starObjectService.saveOneStarObject(starObject)).thenReturn(updatedStarObject);
        var actual = starObjectController.updateStarObject(21L, updatedStarObject);

        assertEquals(updatedStarObject.getEquatorialDiameter(), actual.get().getEquatorialDiameter(), "should return 2L");
        assertEquals(updatedStarObject.getName(), actual.get().getName(), "should return updatedName");
        assertEquals(updatedStarObject.getDiscoveryDate(), actual.get().getDiscoveryDate(), "should return 2020-01-01");
    }

    private static StarObject buildStarObject(Long id) {
        final var starObject = new StarObject();
        starObject.setId(id);
        starObject.setName("Star Object");
        starObject.setDiscoveryDate(Date.valueOf("1990-04-25"));
        starObject.setEquatorialDiameter(1000000L);
        starObject.setDiscoverySourceId(1L);
        return starObject;
    }
}