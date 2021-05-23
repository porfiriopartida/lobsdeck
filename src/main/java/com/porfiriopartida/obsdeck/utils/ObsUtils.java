package com.porfiriopartida.obsdeck.utils;

public class ObsUtils {
    public static void status(String s, String... params ) {
        System.out.println(String.format(s, params));
    }
    public static void status(String s) {
        System.out.println(String.format(s));
    }
}
