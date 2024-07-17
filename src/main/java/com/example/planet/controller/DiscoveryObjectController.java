package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.model.StarObject;
import com.example.planet.service.DiscoverySourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DiscoveryObjectController {

    private final DiscoverySourceService discoverySourceService;

    @GetMapping("/sources")
    private List<DiscoverySource> getAllSources() {
        return discoverySourceService.getAllSources();
    }

    @GetMapping("/source/id")
    private Optional<DiscoverySource> getSourceById(@RequestParam Long id) {
        return discoverySourceService.getSourceById(id);
    }

    @PostMapping("/discoverySource")
    private DiscoverySource addNewSource(@RequestBody DiscoverySource source) {
        return discoverySourceService.saveOne(source);
    }

    @PostMapping("/discoverySources")
    private List<DiscoverySource> saveAllSources(@RequestBody List<DiscoverySource> source) {
        return discoverySourceService.saveAll(source);
    }
}
