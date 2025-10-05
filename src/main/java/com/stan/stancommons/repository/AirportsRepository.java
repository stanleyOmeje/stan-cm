package com.stan.stancommons.repository;

import com.systemspecs.remita.vending.vendingcommon.entity.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportsRepository extends JpaRepository<Airports, Long> {
    boolean existsByCityCodeAndName(String cityCode, String name);
}
