package com.example.planet.controller;

import com.example.planet.model.StarObject;
import com.example.planet.service.StarObjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarObjectControllerTest {

    @Mock
    StarObjectService starObjectService;

    @InjectMocks
    StarObjectController starObjectController;

    @Test
    void shouldGetOneStarObject() {
        StarObject starObject = buildStarObject(1L);
        when(starObjectService.getStarObjectById(1L)).thenReturn(Optional.of(starObject));
        starObjectController.getStarObjectById(1L);
        assertEquals(Optional.of(starObject),starObjectController.getStarObjectById(1L),"should return object with ID 1");

    }
//TODO Fix
    //@Test
    //void shouldGetAllStarObjects() {
    //    var starObjects = List.of(buildStarObject(2L),buildStarObject(3L),buildStarObject(4L));
    //    when(starObjectService.getAllStarObjects(0,10)).thenReturn((Page<StarObject>) starObjects);
    //    starObjectController.getAllStarObjects(0,10);
    //    assertEquals(3,starObjects.size(),"should return 3 objects");
    //    assertEquals(2L,starObjects.get(0).getId(),"should return 2 objects");
    //    assertEquals(3L,starObjects.get(1).getId(),"should return 3 objects");
    //    assertEquals(4L,starObjects.get(2).getId(),"should return 4 objects");
    //}

    @Test
    void shouldAddNewStarObjectObject(){
       var starObject = buildStarObject(21L);
       when(starObjectService.saveOneStarObject(starObject)).thenReturn(starObject);
       starObjectController.addNewStarObject(starObject);
       assertEquals(starObject, starObjectController.addNewStarObject(starObject),"should return object with ID 21");
    }

    @Test
    void shouldaddManyStarObjects(){
        var starObjects = List.of(buildStarObject(2L),buildStarObject(3L),buildStarObject(4L));
        when(starObjectService.saveManyStarObjects(starObjects)).thenReturn(starObjects);
        starObjectController.addManyStarObjects(starObjects);
        assertEquals(3,starObjects.size(),"should return 3 objects");
    }

    private static StarObject buildStarObject(Long id) {
        var starObject = new StarObject();
        starObject.setId(id);
        starObject.setName("Star Object");
        starObject.setDiscoveryDate(Date.valueOf("1990-04-25"));
        starObject.setEquatorialDiameter(1000000L);
        starObject.setDiscoverySourceId(1L);
        return starObject;
    }
}