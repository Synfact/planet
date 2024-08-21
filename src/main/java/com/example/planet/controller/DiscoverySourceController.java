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


@RestController
@RequiredArgsConstructor
public class DiscoverySourceController {

    private final DiscoverySourceService discoverySourceService;

    private final PagedResourcesAssembler<DiscoverySource> assembler;

    @RequestMapping(value = "/sources", method = RequestMethod.GET)
    public ResponseEntity<PagedModel<EntityModel<DiscoverySource>>> getAllDiscoverySources(@RequestParam(defaultValue = "0") int page,
                                                                                           @RequestParam(defaultValue = "10") int size) {
        var discoverySources = assembler.toModel(discoverySourceService.getAllDiscoverySources(page, size));
        return new ResponseEntity<>(discoverySources, HttpStatus.OK);
    }

    @RequestMapping(value = "/sources/{id}", method = RequestMethod.GET)
    public ResponseEntity<DiscoverySource> getDiscoverySourceById(@PathVariable Long id) {
        var discoverySource = discoverySourceService.getDiscoverySourceById(id);
        return new ResponseEntity<>(discoverySource, HttpStatus.OK);
    }

    @PostMapping("/discoverySources")
    public List<DiscoverySource> addManyDiscoverySources(@RequestBody List<DiscoverySource> source) {
        return discoverySourceService.saveAllDiscoverySources(source);
    }

    @PutMapping("/discoverySources/{id}")
    public DiscoverySource updateDiscoverySource(@PathVariable Long id, @RequestBody DiscoverySource discoverySourceDetails) {
        return discoverySourceService.updateDiscoverySource(id, discoverySourceDetails);
    }
}