package com.stan.stancommons.movies.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingResponse {

    private String message;
    private Boolean status;
    private DataDto data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {

        private Long id;

        @JsonProperty("product_reference")
        private String productReference;

        @JsonProperty("user_id")
        private String userId;

        private String name;
        private String email;
        private String phone;

        @JsonProperty("vendor_id")
        private String vendorId;

        @JsonProperty("listing_id")
        private String listingId;

        @JsonProperty("listing_name")
        private String listingName;

        private String service;
        private String type;

        @JsonProperty("transaction_id")
        private String transactionId;

        @JsonProperty("ticket_quantity")
        private Integer ticketQuantity;

        @JsonProperty("ticket_amount")
        private String ticketAmount;

        @JsonProperty("ticket_fee")
        private String ticketFee;

        private String status;
        private String channel;

        @JsonProperty("is_free")
        private Boolean isFree;

        @JsonProperty("start_date")
        private String startDate;

        @JsonProperty("end_date")
        private String endDate;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;

        @JsonProperty("user_type")
        private String userType;

        @JsonProperty("booking_ref")
        private String bookingRef;

        @JsonProperty("vendor_name")
        private String vendorName;

        @JsonProperty("vendor_email")
        private String vendorEmail;

        @JsonProperty("check_in_status")
        private String checkInStatus;

        private String address;

        @JsonProperty("coupon_code")
        private String couponCode;

        @JsonProperty("discount_amount")
        private String discountAmount;

        @JsonProperty("amount_paid")
        private String amountPaid;

        @JsonProperty("coupon_creator_user_type")
        private String couponCreatorUserType;

        @JsonProperty("rescheduled_at")
        private String rescheduledAt;

        private String venue;

        @JsonProperty("old_booking_ref")
        private String oldBookingRef;

        @JsonProperty("age_range")
        private String ageRange;

        private String session;

        @JsonProperty("expires_at")
        private String expiresAt;
    }

    public CreateBookingResponse(String message, boolean b) {
        this.message= message;
        this.status = b;
    }
}
