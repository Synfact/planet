package com.example.planet.service;

import com.example.planet.model.DiscoverySource;
import com.example.planet.repository.DiscoverySourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscoverySourceService {

    private final DiscoverySourceRepository discoverySourceRepository;

    public Page<DiscoverySource> getAllDiscoverySources(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return discoverySourceRepository.findAll(pageable);
    }

    public DiscoverySource getDiscoverySourceById(Long id) {
        return discoverySourceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public DiscoverySource saveOneDiscoverySource(final DiscoverySource discoverySource) {
        return discoverySourceRepository.save(discoverySource);
    }

    public List<DiscoverySource> saveAllDiscoverySources(final List<DiscoverySource> discoverySources) {
        return discoverySourceRepository.saveAll(discoverySources);
    }

    public DiscoverySource updateDiscoverySource(Long id, final DiscoverySource discoverySource) {
        Optional<DiscoverySource> existingSource = discoverySourceRepository.findById(id);
        if (existingSource.isPresent()) {
            var object = existingSource.get();
            return checkIfUpdateSourceNeeded(discoverySource, object);
        }
        return discoverySource;
    }

    private DiscoverySource checkIfUpdateSourceNeeded(DiscoverySource discoverySource, DiscoverySource existingSource) {
        if(Objects.nonNull(discoverySource.getName())){
            existingSource.setName(discoverySource.getName());
        }
        if(Objects.nonNull(discoverySource.getType())){
            existingSource.setType(discoverySource.getType());
        }
        if(Objects.nonNull(discoverySource.getEstablishmentDate())){
            existingSource.setEstablishmentDate(discoverySource.getEstablishmentDate());
        }
        if(Objects.nonNull(discoverySource.getStateOwner())){
            existingSource.setStateOwner(discoverySource.getStateOwner());
        }
        return discoverySourceRepository.save(existingSource);
    }
}
