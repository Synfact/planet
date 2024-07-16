package com.example.planet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DiscoverySource implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date establishmentDate;

    private String type;

    private String stateOwner;


}
