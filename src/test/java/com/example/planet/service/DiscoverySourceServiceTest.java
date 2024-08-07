package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscoverySourceServiceTest {

    @Mock
    DiscoverySourceRepository discoverySourceRepository;

    @InjectMocks
    DiscoverySourceService discoverySourceService;


    @Test
    void shouldGetOneDiscoverySourceById() {
        DiscoverySource discoverySource = buildDiscoverySource(1L);
        discoverySourceService.getDiscoverySourceById(1L);
        assertEquals(1L, discoverySource.getId(),"discovery source should be 1L");
        assertEquals("Hubble", discoverySource.getName(), "discovery source should be Hubble");
        assertEquals("USA", discoverySource.getStateOwner(), "discovery source should be USA");
        assertEquals((Date.valueOf("1990-04-25")), discoverySource.getEstablishmentDate(), "discovery source should be 1990-04-25");
        assertEquals("Telescope", discoverySource.getType(), "discovery source should be Telescope");

    }

    @Test
    void shouldGetAllDiscoverySources(){
        var discoverySources = List.of(buildDiscoverySource(1L), buildDiscoverySource(2L));
        final Pageable pageable = PageRequest.of(0,2);

        when(discoverySourceService.getAllDiscoverySources(0,2)).thenReturn(new PageImpl<>(discoverySources,pageable,2));

        var sources = discoverySourceService.getAllDiscoverySources(0,2);

        assertEquals(2, sources.getTotalElements(), "should return 3 discovery sources");
        assertEquals(1L, Objects.requireNonNull(sources.getContent().get(0).getId(), "discovery source id should be 1"));
        assertEquals(2L, Objects.requireNonNull(sources.getContent().get(1).getId(), "discovery source id should be 2"));
    }

    @Test
    void shouldSaveOneDiscoverySourceSource(){
        var discoverySource = buildDiscoverySource(3L);
        discoverySourceService.saveOneDiscoverySource(discoverySource);
        assertEquals(3, discoverySource.getId(),"discovery source should have ID 3");
        assertEquals("Hubble", discoverySource.getName(), "discovery source should be called Hubble");
    }

    @Test
    void shouldsaveManySources(){
        var discoverySources = List.of(buildDiscoverySource(5L), buildDiscoverySource(6L), buildDiscoverySource(7L));
        discoverySourceService.saveAllDiscoverySources(discoverySources);
        assertEquals(3, discoverySources.size(),"discovery sources should have 3 elements");
        assertEquals(5L,discoverySources.get(0).getId(),"discovery source should have id 5");
        assertEquals(6L,discoverySources.get(1).getId(),"discovery source should have id 6");
        assertEquals(7L,discoverySources.get(2).getId(),"discovery source should have id 7");
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