package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.service.DiscoverySourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscoverySourceControllerTest {

    @Mock
    DiscoverySourceService discoverySourceService;

    @Mock
    PagedResourcesAssembler<DiscoverySource> assembler;

    @InjectMocks
    DiscoverySourceController discoverySourceController;


    @Test
    void shouldGetOneDiscoverySourceByID() {
        DiscoverySource discoverySource = buildDiscoverySource(1L);
        when(discoverySourceService.getDiscoverySourceById(1L)).thenReturn(discoverySource);

        discoverySourceController.getDiscoverySourceById(1L);

        assertEquals(Optional.of(discoverySource), discoverySourceController.getDiscoverySourceById(1L));
    }

    @Test
    void shouldGetAllDiscoverySources() {
        final var discoverySources = List.of(buildDiscoverySource(1L), buildDiscoverySource(2L), buildDiscoverySource(8L));
        final PageImpl<DiscoverySource> page = new PageImpl<>(discoverySources);
        final PagedModel<EntityModel<DiscoverySource>> pagedModel = PagedModel.wrap(discoverySources,new PagedModel.PageMetadata(1,1,1));

        when(discoverySourceService.getAllDiscoverySources(0,3)).thenReturn(page);
        when(assembler.toModel(page)).thenReturn(pagedModel);

        final var actual = discoverySourceController.getAllDiscoverySources(0,3);

        assertEquals(3, actual.getBody().getContent().stream().toList().size(), "should return 3 discovery sources");
        assertEquals(1L,actual.getBody().getContent().stream().toList().get(0).getContent().getId(), "1st element should have id 1");
        assertEquals(2L,actual.getBody().getContent().stream().toList().get(1).getContent().getId(), "2st element should have id 2");
        assertEquals(8L,actual.getBody().getContent().stream().toList().get(2).getContent().getId(), "3st element should have id 8");
    }

    @Test
    void shouldAddManyDiscoverySources() {
        final var discoverySources = List.of(buildDiscoverySource(1L), buildDiscoverySource(2L));

        when(discoverySourceService.saveAllDiscoverySources(discoverySources)).thenReturn(discoverySources);
        discoverySourceController.addManyDiscoverySources(discoverySources);

        assertEquals(2, discoverySources.size());
    }

    @Test
    void shouldUpdateDiscoverySource() {
        final var discoverySource = buildDiscoverySource(1L);
        final var updatedDiscoverySource = new DiscoverySource();
        updatedDiscoverySource.setName("updatedName");
        updatedDiscoverySource.setId(1L);
        updatedDiscoverySource.setEstablishmentDate(Date.valueOf("2000-07-12"));
        updatedDiscoverySource.setType("newType");
        updatedDiscoverySource.setStateOwner("owner");

        when(discoverySourceService.updateDiscoverySource(discoverySource.getId(),updatedDiscoverySource)).thenReturn(discoverySource);
        var actualDiscoverySource = discoverySourceService.updateDiscoverySource(1L,updatedDiscoverySource);

        assertEquals(discoverySource.getName(), actualDiscoverySource.getName(), "updated name should be updatedName");
        assertEquals(discoverySource.getType(), actualDiscoverySource.getType(), "updated type should be newType");
        assertEquals(discoverySource.getStateOwner(), actualDiscoverySource.getStateOwner(), "updated state owner should be owner");
    }

    private static DiscoverySource buildDiscoverySource(Long id) {
        final var discoverySource = new DiscoverySource();
        discoverySource.setId(id);
        discoverySource.setName("Hubble");
        discoverySource.setEstablishmentDate(Date.valueOf("1990-04-25"));
        discoverySource.setType("Telescope");
        discoverySource.setStateOwner("USA");
        return discoverySource;
    }
}