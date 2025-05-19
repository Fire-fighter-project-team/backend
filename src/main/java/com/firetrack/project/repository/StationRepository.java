package com.firetrack.project.repository;

import com.firetrack.project.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByCityAndFireStation(String city, String fireStation);
}

