package com.example.vitly.util;

public class Base62Encoder {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final long SECRET = 123456789L;
    private static final long PRIME = 1000003L;
    private static final int LENGTH = 7;

    public static String generate(Long id) {
        long scrambled = (id * PRIME) ^ SECRET;
        String encoded = encodeBase62(scrambled);
        return normalize(encoded);
    }

    private static String encodeBase62(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(BASE62.charAt((int) (num % 62)));
            num /= 62;
        }

        return sb.reverse().toString();
    }

    private static String normalize(String str) {

        if (str.length() > LENGTH) {
            return str.substring(str.length() - LENGTH); // take last 7
        }

        StringBuilder sb = new StringBuilder();

        while (sb.length() + str.length() < LENGTH) {
            sb.append('0');
        }

        sb.append(str);
        return sb.toString();
    }
}