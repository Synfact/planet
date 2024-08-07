package com.example.planet.repository;

import com.example.planet.model.DiscoverySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoverySourceRepository extends JpaRepository<DiscoverySource,Long>, PagingAndSortingRepository<DiscoverySource,Long> {
}
