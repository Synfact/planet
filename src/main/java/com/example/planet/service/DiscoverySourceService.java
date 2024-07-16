package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscoverySourceService {

    private final DiscoverySourceRepository discoverySourceRepository;

    public List<DiscoverySource> getAllSources() {
        return discoverySourceRepository.findAll();
    }

    public DiscoverySource saveOne(final DiscoverySource discoverySource) {
        return discoverySourceRepository.save(discoverySource);
    }

}
