package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.service.DiscoverySourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscoverySourceControllerTest {

    @Mock
    DiscoverySourceService discoverySourceService;

    @InjectMocks
    DiscoverySourceController discoverySourceController;


    @Test
    void shouldGetOneSourceByID(){
        DiscoverySource discoverySource = buildDiscoverySource(1L);
        when(discoverySourceService.getSourceById(1L)).thenReturn(Optional.of(discoverySource));
        discoverySourceController.getSourceById(1L);
        assertEquals(Optional.of(discoverySource), discoverySourceController.getSourceById(1L));
    }

    @Test
    void shouldGetAllSources(){
        var discoverySources = List.of(buildDiscoverySource(1L),buildDiscoverySource(2L),buildDiscoverySource(8L));
        when(discoverySourceService.getAllSources()).thenReturn(discoverySources);
        discoverySourceController.getAllSources();
        assertEquals(3, discoverySources.size());
    }

    @Test
    void shouldAddNewSource(){
        var discoverySource = buildDiscoverySource(21L);
        when(discoverySourceService.saveOne(discoverySource)).thenReturn(discoverySource);
        discoverySourceController.addNewSource(discoverySource);
        assertEquals(discoverySource, discoverySourceController.addNewSource(discoverySource));
    }

    @Test
    void shouldSaveAllSources(){
        var discoverySources = List.of(buildDiscoverySource(1L),buildDiscoverySource(2L));
        when(discoverySourceService.saveAll(discoverySources)).thenReturn(discoverySources);
        discoverySourceController.saveAllSources(discoverySources);
        assertEquals(2, discoverySources.size());
    }

    private static DiscoverySource buildDiscoverySource(Long id){
        var discoverySource = new DiscoverySource();
        discoverySource.setId(id);
        discoverySource.setName("Hubble");
        discoverySource.setEstablishmentDate(Date.valueOf("1990-04-25"));
        discoverySource.setType("Telescope");
        discoverySource.setStateOwner("USA");
        return discoverySource;
    }
}