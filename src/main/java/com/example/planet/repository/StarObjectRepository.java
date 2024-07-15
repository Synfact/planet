package com.example.planet.repository;

import com.example.planet.model.StarObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarObjectRepository extends JpaRepository<StarObject,Integer> {

}
