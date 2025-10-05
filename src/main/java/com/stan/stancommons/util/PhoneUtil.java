package com.stan.stancommons.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class PhoneUtil {
    private static final String NIGERIAN_COUNTRY_CODE = "NGA";
    public static boolean validNigeriaPhoneNumber(String number) {
        if (StringUtils.isBlank(number)) {
            return false;
        }

        String formattedNumber = number;
        if (!formattedNumber.startsWith("+234")) {
            if (formattedNumber.startsWith("0") && formattedNumber.length() == 11) {
                formattedNumber = "+234" + formattedNumber.substring(1);
            } else if (formattedNumber.startsWith("234") && formattedNumber.length() == 13) {
                formattedNumber = "+" + formattedNumber;
            } else {
                return false;
            }
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber nigeriaNumber = phoneNumberUtil.parse(formattedNumber, NIGERIAN_COUNTRY_CODE);
            boolean checkValidity = phoneNumberUtil.isValidNumberForRegion(nigeriaNumber, NIGERIAN_COUNTRY_CODE);
            if (!checkValidity) {
                return true;
            } else {
                return checkValidity;
            }
        } catch (NumberParseException e) {
            log.info("PhoneNumber not parsable for Nigeria: => {}", e.getMessage());
            return false;
        }
    }

    public static boolean isValidIntlPhoneNumber(String countryIsoCode, String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)  || StringUtils.isBlank(countryIsoCode)) {
            return false;
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, countryIsoCode);
            return phoneNumberUtil.isValidNumber(parsedPhoneNumber);
        } catch (NumberParseException e) {
            log.info("PhoneNumber not parsable for the given country code: {}", e.getMessage());
            return false;
        }
    }

    public static boolean isValidPhoneNumber(String countryIsoCode, String phoneNumber) {
        if (NIGERIAN_COUNTRY_CODE.equalsIgnoreCase(countryIsoCode)) {
            return validNigeriaPhoneNumber(phoneNumber);
        } else {
            return isValidIntlPhoneNumber(countryIsoCode, phoneNumber);
        }
    }
}
