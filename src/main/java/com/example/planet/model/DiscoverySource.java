package com.example.planet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public record DiscoverySource(@Id Long id, String name, String establishmentDate, String type, String stateOwner) {

}
