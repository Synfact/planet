package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscoverySourceService {

    private final DiscoverySourceRepository discoverySourceRepository;

    public Page<DiscoverySource> getAllDiscoverySources(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return discoverySourceRepository.findAll(pageable);
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
