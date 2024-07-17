package com.example.planet.service;

import com.example.planet.model.StarObject;
import com.example.planet.repository.DiscoverySourceRepository;
import com.example.planet.repository.StarObjectRepository;
import org.hibernate.sql.ast.tree.expression.Star;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarObjectServiceTest {

    @Mock
    StarObjectRepository starObjectRepository;

    @InjectMocks
    StarObjectService starObjectService;

    @BeforeEach
    void setUp(){}

    @Test
    void getObjectById() {
        StarObject starObject = buildStarObject(1L);
        starObjectService.getObjectById(1L);
        assertEquals(1L, starObject.getId(),"starObject should have id 1");
        assertEquals("Star Object", starObject.getName(),"starObject should have name");
        assertEquals(Date.valueOf("1990-04-25"),starObject.getDiscoveryDate(),"starObject should have discovery date");
        assertEquals(1000000L,starObject.getEquatorialDiameter(),"starObject should have equatorial diameter");
        assertEquals(1L,starObject.getDiscoverySourceId(),"starObject should have discovery source id");
    }

    @Test
    void getAllObjects() {
        var starObjects = List.of(buildStarObject(1L),buildStarObject(3L),buildStarObject(4L));
        when(starObjectRepository.findAll()).thenReturn(starObjects);
        List<StarObject> objects = starObjectService.getAllObjects();
        assertEquals(3, objects.size(),"objects should have 3 objects");
        assertEquals(1L, objects.get(0).getId(),"objects should have id 1");
        assertEquals(3L, objects.get(1).getId(),"objects should have id 3");
        assertEquals(4L, objects.get(2).getId(),"objects should have id 4");
    }

    @Test
    void saveMany() {
        var starObjects = List.of(buildStarObject(2L),buildStarObject(5L));
        when(starObjectService.saveMany(starObjects)).thenReturn(starObjects);
        starObjectService.saveMany(starObjects);
        assertEquals(2, starObjects.size(),"objects should have 2 objects");
        assertEquals(2L, starObjects.get(0).getId(),"objects should have id 2");
        assertEquals(5L, starObjects.get(1).getId(),"objects should have id 5");
    }

    @Test
    void saveOne() {
        var starObject = buildStarObject(77L);
        starObjectService.saveOne(starObject);
        assertEquals(77L, starObject.getId(),"starObject should have id 77");
        assertEquals("Star Object", starObject.getName(),"starObject should have name");
    }

    private static StarObject buildStarObject(Long id){
        var starObject = new StarObject();
        starObject.setId(id);
        starObject.setName("Star Object");
        starObject.setDiscoveryDate(Date.valueOf("1990-04-25"));
        starObject.setEquatorialDiameter(1000000L);
        starObject.setDiscoverySourceId(1L);
        return starObject;
    }
}