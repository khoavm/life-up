package com.khoavm.lifeup.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;


public class Util {
    public static String generateSecureRandomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int numberOfChars = alphabet.length();
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numberOfChars);
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }
}
