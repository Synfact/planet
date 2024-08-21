package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.model.StarObject;
import com.example.planet.repository.DiscoverySourceRepository;
import com.example.planet.repository.StarObjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarObjectServiceTest {

    @Mock
    StarObjectRepository starObjectRepository;

    @Mock
    DiscoverySourceRepository discoverySourceRepository;

    @InjectMocks
    StarObjectService starObjectService;


    @Test
    void getStarObjectById() {
        final var starObjects = buildStarObject(6L);
        when(starObjectRepository.findById(6L)).thenReturn(Optional.of(starObjects));

        var objects = starObjectService.getStarObjectById(6L);
        assertEquals(6L, objects.getId(),"starObject should have id 6");
        assertEquals("Star Object", objects.getName(),"starObject should have name");
        assertEquals(Date.valueOf("1990-04-25"),objects.getDiscoveryDate(),"starObject should have discovery date");
        assertEquals(1000000L,objects.getEquatorialDiameter(),"starObject should have equatorial diameter");
        assertEquals(1L,objects.getDiscoverySourceId(),"starObject should have discovery source id");
    }

    @Test
    void getAllStarObjects() {
        final var starObjects = List.of(buildStarObject(1L), buildStarObject(3L), buildStarObject(4L));
        final Pageable pageable = PageRequest.of(0, 10);

        when(starObjectRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(starObjects, pageable, 3));
        var objects = starObjectService.getAllStarObjects(0, 10);

        assertEquals(3, objects.getTotalElements(), "objects should have 3 objects");
        assertEquals(1L, objects.getContent().get(0).getId(), "objects should have id 1");
        assertEquals(3L, objects.getContent().get(1).getId(), "objects should have id 3");
        assertEquals(4L, objects.getContent().get(2).getId(), "objects should have id 4");
    }

    @Test
    void saveManyStarObjects() {
        var object1 = buildStarObject(1L);
        var object2 = buildStarObject(2L);

        var source = new DiscoverySource();
        final var starObjects = List.of(object1, object2);
        when(starObjectRepository.saveAll(starObjects)).thenReturn(starObjects);
        when(discoverySourceRepository.findById(anyLong())).thenReturn(Optional.of(source));

        starObjectService.saveManyStarObjects(starObjects);

        assertEquals(2, starObjects.size(), "objects should have 2 objects");
        assertEquals(1L, starObjects.get(0).getId(), "objects should have id 2");
        assertEquals(2L, starObjects.get(1).getId(), "objects should have id 5");
    }

    @Test
    void saveOneStarObjectStarObject() {
        var object1 = buildStarObject(77L);
        var source = new DiscoverySource();
        final var starObjects = List.of(object1);

        when(starObjectRepository.saveAll(starObjects)).thenReturn(starObjects);
        when(discoverySourceRepository.findById(anyLong())).thenReturn(Optional.of(source));
        final var starObject = buildStarObject(77L);

        starObjectService.saveManyStarObjects(starObjects);
        assertEquals(77L, starObject.getId(),"starObject should have id 77");
        assertEquals("Star Object", starObject.getName(),"starObject should have name");
    }

    private static StarObject buildStarObject(Long id) {
        final var starObject = new StarObject();
        starObject.setId(id);
        starObject.setName("Star Object");
        starObject.setDiscoveryDate(Date.valueOf("1990-04-25"));
        starObject.setEquatorialDiameter(1000000L);
        starObject.setDiscoverySourceId(1L);
        starObject.setMass(1000L);
        return starObject;
    }
}