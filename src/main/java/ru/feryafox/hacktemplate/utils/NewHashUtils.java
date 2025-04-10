package ru.feryafox.hacktemplate.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class NewHashUtils {
    public static String getSHA256Hash(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }
}
