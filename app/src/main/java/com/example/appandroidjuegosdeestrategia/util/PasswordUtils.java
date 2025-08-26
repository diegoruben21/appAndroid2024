package com.example.appandroidjuegosdeestrategia.util;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 2100000; // 2.1 millones
    private static final int KEY_LENGTH = 256; // 256 bits

    // Generar salt (16 bytes aleatorios)
    public static String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        return Base64.encodeToString(salt, Base64.NO_WRAP);
    }

    // Hashear password con salt
    public static String hashPassword(String password, String saltStr) {
        try {
            byte[] salt = Base64.decode(saltStr, Base64.NO_WRAP);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.encodeToString(hash, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error generando hash", e);
        }
    }
}