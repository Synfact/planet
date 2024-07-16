package com.example.planet.controller;

import com.example.planet.model.DiscoverySource;
import com.example.planet.model.StarObject;
import com.example.planet.service.DiscoverySourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiscoveryObjectController {

    private final DiscoverySourceService discoverySourceService;

    @GetMapping("/sources")
    private List<DiscoverySource> getAllSources(){
        return discoverySourceService.getAllSources();
    }

    @PostMapping("/discoverySource")
    private DiscoverySource addNewSource(@RequestBody DiscoverySource source){
        return discoverySourceService.saveOne(source);
    }
}
