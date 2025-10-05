package com.stan.stancommons.eventsticketing.repository;

import com.systemspecs.remita.vending.vendingcommon.eventsticketing.entity.EventListingBookingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventBookingDataRepository extends JpaRepository<EventListingBookingData, Long> {
    Optional<EventListingBookingData> findByBookingId(String bookingId);
}
