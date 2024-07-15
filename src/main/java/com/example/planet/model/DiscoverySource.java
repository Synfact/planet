package com.example.planet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class DiscoverySource {
    @Id
    private Long id;

    private String name;

    private String establishmentDate;

    private String type;

    private String stateOwner;
}
