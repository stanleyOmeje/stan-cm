package com.stan.stancommons.flight.repository;

import com.systemspecs.remita.vending.vendingcommon.flight.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
    Optional<FlightBooking> findByBookingIdentifierAfter(String bookingIdentifier);
}
