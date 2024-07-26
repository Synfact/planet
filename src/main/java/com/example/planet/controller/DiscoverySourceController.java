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
    public List<DiscoverySource> getAllSources() {
        System.out.println("getAllSources");
        return discoverySourceService.getAllSources();
    }

    @GetMapping("/source/id")
    public Optional<DiscoverySource> getSourceById(@RequestParam Long id) {
        return discoverySourceService.getSourceById(id);
    }

    @PostMapping("/discoverySource")
    public DiscoverySource addNewSource(@RequestBody DiscoverySource source) {
        return discoverySourceService.saveOne(source);
    }

    @PostMapping("/discoverySources")
    public List<DiscoverySource> saveAllSources(@RequestBody List<DiscoverySource> source) {
        System.out.println("addNewSources");
        return discoverySourceService.saveAll(source);
    }
}
