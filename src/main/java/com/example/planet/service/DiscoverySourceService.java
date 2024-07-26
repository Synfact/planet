package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscoverySourceService {

    private final DiscoverySourceRepository discoverySourceRepository;

    public List<DiscoverySource> getAllDiscoverySources() {
        return discoverySourceRepository.findAll();
    }

    public Optional<DiscoverySource> getDiscoverySourceById(Long id) {
        return discoverySourceRepository.findById(id);
    }

    public DiscoverySource saveOneDiscoverySource(final DiscoverySource discoverySource) {
        return discoverySourceRepository.save(discoverySource);
    }

    public List<DiscoverySource> saveAllDiscoverySources(final List<DiscoverySource> discoverySources) {
        return discoverySourceRepository.saveAll(discoverySources);
    }
}
