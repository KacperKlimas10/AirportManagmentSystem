package org.pl.serwis_logownia.utils;

import org.pl.serwis_logownia.entities.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashHandler {
    public static String sha256(final String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static boolean validateUser(User user, String db_hash) {
        return HashHandler.sha256(user.getHasło()).equals(db_hash);
    }
}
