package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import com.example.planet.service.DiscoverySourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DiscoverySourceController {

    private final DiscoverySourceService discoverySourceService;

    private final DiscoverySourceRepository discoverySourceRepository;

    @GetMapping("/sources")
    public List<DiscoverySource> getAllDiscoverySources() {
        System.out.println("getAllSources");
        return discoverySourceService.getAllDiscoverySources();
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