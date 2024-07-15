package com.example.planet.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public record StarObject(@Id Long id,
                         String text,
                         Long mass,
                         Long equatorialDiameter,
                         Long surfaceTemperature,
                         Date discoveryDate,
                         DiscoverySource discoverySource,
                         Type type) { }