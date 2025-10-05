package com.stan.stancommons.enums;

public enum TransactionStatus {
    SUCCESS("00", "Success"),
    APPROVED("00", "Approved"),
    CONFIRMED("00", "Transaction Confirmed"),
    COMPLETED("00", "Transaction Completed"),
    TRANSACTION_FAILED("01", "Transaction failed"),
    BAD_REQUEST("02", "Bad Request/Customer info could not be retrieved"),
    PENDING("03", "Transaction pending"),
    AUTHENTICATION_ERROR("04", "Authentication error/You cannot vend for this customer"),
    IP_NOT_WHITELISTED("06", "IP Not Whitelisted"),
    LIMIT_REACHED("08", "Limit reached"),
    INVALID_TRANSACTION_REFERENCE("12", "Invalid Transaction Reference"),
    INSUFFICIENT_AMOUNT("10", "Insufficient amount"),
    INSUFFICIENT_BALANCE("11", "Insufficient balance"),
    INVALID_CHANNEL("17", "nvalid Channel"),
    SYSTEM_ERROR("21", "System error"),
    UNKNOWN_PAYMENT_REFERENCE("22", "Unknown payment reference"),
    INVALID_EXCHANGE_REQUEST("23", "Invalid exchange request/similar transaction"),
    USER_VERIFICATION_REQUIRED("24", "User verification required"),
    AMOUNT_EXCEEDED("25", "Amount exceeded"),
    DUPLICATE_RECORD("26", "Duplicate Record"),
    INVALID_UNIQUE_IDENTIFIER("31", "Invalid phone number"),
    INACTIVE_PRODUCT("32", "Inactive Product"),
    UNAUTHORIZED_DEBIT_ACCOUNT("38", "Unauthorized Debit Account"),
    INSUFFICIENT_FUND("51", "Insufficient Funds"),
    TRANSACTION_LIMIT_REACHED("62", "Transaction Limit Reached"),
    UNKNOWN_CODE("71", "Unknown code"),
    DUPLICATE_TRANSACTION("94", "Duplicate Transaction"),
    SYSTEM_MALFUNCTION("96", "System Malfunction"),
    FALL_BACK_PROCESSOR_FAILED("98", "Fall Back Transaction failed"),
    TRANSACTION_NOT_FOUND("99", "Transaction Not Found"),
    REVERSAL_FAILED("93", "Reversal Failed"),
    REVERSAL_SUCCESSFUL("92", "Reversal Successful"),
    REVERSED_BY_PROVIDER("90", "Transaction failed and has being reversed by provider"),
    QUERY_TRANSACTION_FAILED("91", "Query Transaction Returned Failed from third party"),
    REVERSED("89", "reversed"),
    NO_MOVIES_FOUND("90", "No Movies Found"),
    GET_MOVIES_FAILED("88", "Failed to fetch all movies"),
    PROCESSOR_NOT_FOUND("89","Processor Package Not Found"),
    GET_MOVIE_BY_NAME_FAILED("87", "Failed to fetch movie by name"),
    GET_MOVIE_SHOWTIME_FAILED("86", "Failed to fetch movie showtime"),
    SHOWTIME_NOT_FOUND("85", "No Show times Found for Cinema"),
    CREATE_BOOKING_FAILED("84", "Failed to create booking request"),
    BOOKING_NOT_CREATED("83", "No Booking created"),
    COMPLETE_BOOKING_FAILED("82", "Failed to complete booking request");






    private String code;
    private String message;

    TransactionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
