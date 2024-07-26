package com.example.planet.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_discovery_source")
@Getter
@Setter
@RequiredArgsConstructor
public class DiscoverySource implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date establishmentDate;

    private String type;

    private String stateOwner;
}
