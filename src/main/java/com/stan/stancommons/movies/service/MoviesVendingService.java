package com.stan.stancommons.movies.service;

import com.systemspecs.remita.vending.vendingcommon.movies.dto.request.*;
import com.systemspecs.remita.vending.vendingcommon.movies.dto.response.*;

public interface MoviesVendingService {
    MovieResponse fetchMovies(GetAllMoviesRequest request, MovieParam movieParam);
    SingleMoviesResponseDto fetchMoviesByName(GetMovieByName request);
    MovieShowtimeResponse fetchMoviesShowTimes(GetShowTimeRequest request);
    CreateBookingsResponse createMovieBooking(CreateMovieBookingRequest request);
    CompleteMovieBookingResult completeMovieBooking(CompleteMovieBookingRequest request);
}
