package com.loosu.acamera.utils;

public class MathUtil {
    private MathUtil(){
        // tools class no instance
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;

    }
}
