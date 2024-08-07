package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import com.example.planet.service.DiscoverySourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@RestController
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class DiscoverySourceController {

    private final DiscoverySourceService discoverySourceService;

    private final DiscoverySourceRepository discoverySourceRepository;

    @GetMapping("/sources")
    public ResponseEntity<Page<DiscoverySource>> getAllDiscoverySources(int page, int size) {
        System.out.println("getAllSources");
        Page <DiscoverySource> discoverySources = discoverySourceService.getAllDiscoverySources(page, size);

        return new ResponseEntity<>(discoverySources, HttpStatus.OK);
    }

    @GetMapping("/source/{id}")
    public Optional<DiscoverySource> getDiscoverySourceById(@RequestParam Long id) {
        return discoverySourceService.getDiscoverySourceById(id);
    }

    @PostMapping("/discoverySource")
    public DiscoverySource addNewDiscoverySource(@RequestBody DiscoverySource source) {
        return discoverySourceService.saveOneDiscoverySource(source);
    }

    @PostMapping("/discoverySources")
    public List<DiscoverySource> addManyDiscoverySources(@RequestBody List<DiscoverySource> source) {
        System.out.println("addNewSources");
        return discoverySourceService.saveAllDiscoverySources(source);
    }

    @PutMapping("/discoverySource/{id}")
    public Optional<DiscoverySource> updateDiscoverySource(@PathVariable Long id, @RequestBody DiscoverySource discoverySourceDetails) {
        return discoverySourceRepository.findById(id)
                .map(discoverySource -> {
                    discoverySource.setName(discoverySourceDetails.getName());
                    discoverySource.setType(discoverySourceDetails.getType());
                    discoverySource.setEstablishmentDate(discoverySourceDetails.getEstablishmentDate());
                    discoverySource.setStateOwner(discoverySourceDetails.getStateOwner());
                    return discoverySourceService.saveOneDiscoverySource(discoverySource);
                });
    }
}