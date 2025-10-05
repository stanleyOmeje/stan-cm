package com.stan.stancommons.flight.service;

import com.systemspecs.remita.vending.vendingcommon.flight.dto.request.*;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.request.ConvertCurrencyRequest;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.response.*;

import javax.validation.Valid;

public interface FlightVendingService {
    FlightResponse searchFlight(SearchFlightRequest searchFlightRequest);
    ConfirmPriceResponse confirmPrice(ValidateFlight confirmPriceRequest);
    BookingResult bookFlight(BookFlightRequest bookFlightRequest);
    CancelFlightResponse cancelFlight(CancelFlightRequest cancelFlightRequest);
    AirportResponse getAirport();
    AirportDetailResponse getAirlines(String processorId);
    ConvertCurrencyResponse convertCurrency(ConvertCurrencyRequest convertCurrencyRequest);
    BookingDetailsResponse getBookedFlightDetails(@Valid BookingDetailsRequest bookingDetailsRequest);
    BalanceResponse getWalletBalance(String processorId);
    //Object getVendingProcessorPackage();
    //Object createVendingProcessorPackage(ProcessorPackageRequest vendingProcessorPackageRequest);
    Object createCabin(@Valid CabinClassRequest request);

    Object fetchCabin();
}

