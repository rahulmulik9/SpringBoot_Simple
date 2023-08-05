package com.SpringBoot.MobileService.Share.dto;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class UsersUtils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABATE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABATE.charAt(RANDOM.nextInt(ALPHABATE.length())));

        }
        return new String(returnValue);
    }
}
