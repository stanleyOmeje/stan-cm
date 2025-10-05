package com.stan.stancommons.eventsticketing.service;

import com.systemspecs.remita.vending.vendingcommon.eventsticketing.dto.request.*;
import com.systemspecs.remita.vending.vendingcommon.eventsticketing.dto.response.*;

public interface EventVendingService {
    ListingResponse getListing(ListingRequest listingRequest, ListingParam listingParam);
    SingleListingResponse getSingleListing(SingleListingRequest singleListingRequest);
    LocationBookingResponse createLocationBooking(LocationTicketBookingRequest locationTicketBookingRequest);
    EventBookingResponse createEventBooking(EventTicketBookingRequest eventTicketBookingRequest);
    CompleteEventBookingResult completeEventBooking(CompleteEventBookingRequest completeEventBookingRequest);
    CategoryResponse getCategory(CategoryRequest categoryRequest);
}

