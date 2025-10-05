package com.stan.stancommons.movies.repository;

import com.systemspecs.remita.vending.vendingcommon.movies.entity.MovieBookingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieBookingDataRepository extends JpaRepository<MovieBookingData, Long> {
    Optional<MovieBookingData> findByBookingId(String bookingId);
}
