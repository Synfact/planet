package com.example.planet.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class StarObject implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long mass;

    private Long equatorialDiameter;

    private String discoveryDate;

    @ManyToOne
    private DiscoverySource discoverySource;

}