package com.metabus.dodream.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Hashing {
    public static String getRandomString(int length){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z
        return new Random().ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String byteToHexString(byte[] data){
        StringBuilder sb = new StringBuilder();
        byte[] var2 = data;
        int var3 = data.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }

        return sb.toString();
    }


    public static String hashingSHA256(String message) throws NoSuchAlgorithmException {
        Charset charset = Charset.forName("UTF-8");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(message.getBytes());
        byte[] hashValue = md.digest();
        return byteToHexString(hashValue);

    }

    public static Long randomPrice(){
        Long[] price = {100000000L, 50000L, 10000L, 5000L, 5000000L, 1000000L, 70000000L};
        int index = (int)(Math.random() * price.length);
        return price[index];
    }
}
