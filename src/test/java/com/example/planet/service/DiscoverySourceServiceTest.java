package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import org.assertj.core.api.SoftAssertions;
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
        when(discoverySourceRepository.findById(discoverySource.getId())).thenReturn(Optional.of(discoverySource));

        var actual = discoverySourceService.getDiscoverySourceById(1L);

        assertEquals(1L, actual.getId(),"discovery source should be 1L");
        assertEquals("Hubble", actual.getName(), "discovery source should be Hubble");
        assertEquals("USA", actual.getStateOwner(), "discovery source should be USA");
        assertEquals((Date.valueOf("1990-04-25")), actual.getEstablishmentDate(), "discovery source should be 1990-04-25");
        assertEquals("Telescope", actual.getType(), "discovery source should be Telescope");

    }

    @Test
    void shouldGetAllDiscoverySources(){
        final var discoverySources = List.of(buildDiscoverySource(1L), buildDiscoverySource(2L));
        final Pageable pageable = PageRequest.of(0,10);

        when(discoverySourceRepository.findAll(PageRequest.of(0,10))).thenReturn(new PageImpl<>(discoverySources,pageable,2));
        final var sources = discoverySourceService.getAllDiscoverySources(0,10);

        assertEquals(2, sources.getTotalElements(), "should return 3 discovery sources");
        assertEquals(1L, sources.getContent().get(0).getId(), "discovery source id should be 1");
        assertEquals(2L, sources.getContent().get(1).getId(), "discovery source id should be 2");
    }

    @Test
    void shouldSaveOneDiscoverySourceSource(){
        final var discoverySource = buildDiscoverySource(3L);

        when(discoverySourceRepository.save(discoverySource)).thenReturn(discoverySource);
        var actual = discoverySourceService.saveOneDiscoverySource(discoverySource);

        assertEquals(3, actual.getId(),"discovery source should have ID 3");
        assertEquals("Hubble", actual.getName(), "discovery source should be called Hubble");
    }

    @Test
    void shouldsaveManySources(){
        final var discoverySources = List.of(buildDiscoverySource(5L), buildDiscoverySource(6L), buildDiscoverySource(7L));

        when(discoverySourceRepository.saveAll(discoverySources)).thenReturn(discoverySources);
        var actual = discoverySourceService.saveAllDiscoverySources(discoverySources);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actual.size()).as("discovery source should have id 5").isEqualTo(3);
            softly.assertThat(actual.get(0).getId()).as("discovery source should have id 5").isEqualTo(5L);
            softly.assertThat(actual.get(1).getId()).as("discovery source should have id 6").isEqualTo(6L);
            softly.assertThat(actual.get(2).getId()).as("discovery source should have id 7").isEqualTo(7L);

        });
    }

    private static DiscoverySource buildDiscoverySource(Long id){
        final var discoverySource = new DiscoverySource();
        discoverySource.setId(id);
        discoverySource.setName("Hubble");
        discoverySource.setEstablishmentDate(Date.valueOf("1990-04-25"));
        discoverySource.setType("Telescope");
        discoverySource.setStateOwner("USA");
        return discoverySource;
    }
}