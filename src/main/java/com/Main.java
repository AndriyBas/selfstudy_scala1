package com;

/**
 * Created by bamboo on 31.07.14.
 */
public class Main {

    final static long A = 24 * 60 * 60 * 1000 * 1000;
    final static long B = 24 * 60 * 60 * 1000;

    public static void main(String[] args) {
        System.out.println((long) (A / B));
        System.out.println(12345 + 54321);

        int x = 1;
        int y = 2;
        x = (y ^= (x ^= y)) ^ x;
        System.out.println("x : " + x + " , y : " + y);

        byte a = 2;
        int b = 3;

        a += b;

        a = (byte) (a + b);

        Object a1 = "a1";
        String b1 = "b1";

        a1 = a1 + b1;

//        a1 += b1;

        System.out.println(a1);
    }

}
