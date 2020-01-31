package com.shm.common.util;

import com.shm.common.exception.InvalidPhoneNumberException;

public class PhoneUtils {
    final static String INTERNAL_PREFIX = "09";
    final static String INTERNATIONAL_PREFIX = "989";

    public static void isStandardPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (!((phoneNumber.startsWith("98") && phoneNumber.length() == 12) ||
                (phoneNumber.startsWith("+98") && phoneNumber.length() == 13) ||
                (phoneNumber.startsWith("0098") && phoneNumber.length() == 14) ||
                (phoneNumber.startsWith("09") && phoneNumber.length() == 11))) {
            throw new InvalidPhoneNumberException();
        }
    }

    public static String makeStandardPhoneNumber(String phoneIdentifier) throws InvalidPhoneNumberException {
        isStandardPhoneNumber(phoneIdentifier);
        if (phoneIdentifier.startsWith(INTERNAL_PREFIX)) {
            String number = phoneIdentifier.substring(INTERNAL_PREFIX.length());
            phoneIdentifier = INTERNATIONAL_PREFIX + number;
        }

        return phoneIdentifier;
    }

    public static String convertPhone(String phoneIdentifier) throws InvalidPhoneNumberException {
        isStandardPhoneNumber(phoneIdentifier);
        String tempPhoneIdentifier = phoneIdentifier.substring(0, INTERNATIONAL_PREFIX.length());
        if (tempPhoneIdentifier.equals(INTERNATIONAL_PREFIX))
            return INTERNAL_PREFIX + phoneIdentifier.substring(INTERNATIONAL_PREFIX.length());
        else
            return phoneIdentifier;
    }
}
