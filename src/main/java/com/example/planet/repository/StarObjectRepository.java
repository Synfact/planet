package com.example.planet.repository;

import com.example.planet.model.StarObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarObjectRepository extends JpaRepository<StarObject,Long>, PagingAndSortingRepository<StarObject,Long> {
}
