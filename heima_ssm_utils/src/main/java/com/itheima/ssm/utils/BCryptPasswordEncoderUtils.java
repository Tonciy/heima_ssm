package com.itheima.ssm.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Zero
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encode(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encode("123"));
    }
}
