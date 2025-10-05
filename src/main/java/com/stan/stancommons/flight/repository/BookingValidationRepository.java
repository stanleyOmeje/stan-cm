package com.stan.stancommons.flight.repository;

import com.systemspecs.remita.vending.vendingcommon.flight.entity.BookingValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingValidationRepository extends JpaRepository<BookingValidation, Long> {
    Optional<BookingValidation> findFirstByBookingCode(String confirmationCode);
}
