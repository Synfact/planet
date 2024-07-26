package com.example.planet.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_star_object")
@Getter
@Setter
@RequiredArgsConstructor
public class StarObject implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long mass;

    private Long equatorialDiameter;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date discoveryDate;

    private Long discoverySourceId;

}