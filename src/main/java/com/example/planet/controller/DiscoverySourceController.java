package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.service.DiscoverySourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class DiscoverySourceController {

    private final DiscoverySourceService discoverySourceService;

    private final PagedResourcesAssembler<DiscoverySource> assembler;

    @GetMapping("/sources")
    public ResponseEntity<PagedModel<EntityModel<DiscoverySource>>> getAllDiscoverySources(int page, int size) {
        var discoverySources = assembler.toModel(discoverySourceService.getAllDiscoverySources(page, size));
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
    public DiscoverySource updateDiscoverySource(@PathVariable Long id, @RequestBody DiscoverySource discoverySourceDetails) {
        return discoverySourceService.updateDiscoverySource(id, discoverySourceDetails);
    }
}