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
    void shouldGetOneDiscoverySourceByID(){
        DiscoverySource discoverySource = buildDiscoverySource(1L);
        when(discoverySourceService.getDiscoverySourceById(1L)).thenReturn(Optional.of(discoverySource));
        discoverySourceController.getDiscoverySourceById(1L);
        assertEquals(Optional.of(discoverySource), discoverySourceController.getDiscoverySourceById(1L));
    }

    @Test
    void shouldGetAllDiscoverySources(){
        var discoverySources = List.of(buildDiscoverySource(1L),buildDiscoverySource(2L),buildDiscoverySource(8L));
        when(discoverySourceService.getAllDiscoverySources()).thenReturn(discoverySources);
        discoverySourceController.getAllDiscoverySources();
        assertEquals(3, discoverySources.size());
    }

    @Test
    void shouldAddNewDiscoverySource(){
        var discoverySource = buildDiscoverySource(21L);
        when(discoverySourceService.saveOneDiscoverySource(discoverySource)).thenReturn(discoverySource);
        discoverySourceController.addNewDiscoverySource(discoverySource);
        assertEquals(discoverySource, discoverySourceController.addNewDiscoverySource(discoverySource));
    }

    @Test
    void shouldAddManyDiscoverySources(){
        var discoverySources = List.of(buildDiscoverySource(1L),buildDiscoverySource(2L));
        when(discoverySourceService.saveAllDiscoverySources(discoverySources)).thenReturn(discoverySources);
        discoverySourceController.addManyDiscoverySources(discoverySources);
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