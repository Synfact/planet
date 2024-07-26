package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.service.DiscoverySourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DiscoverySourceController {

    private final DiscoverySourceService discoverySourceService;

    @GetMapping("/sources")
    public List<DiscoverySource> getAllDiscoverySources() {
        System.out.println("getAllSources");
        return discoverySourceService.getAllDiscoverySources();
    }

    @GetMapping("/source/id")
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
}
