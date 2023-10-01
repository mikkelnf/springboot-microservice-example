package com.mnf.common.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String plainPassword){
        int logRounds = 12;
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(logRounds));

        return hashedPassword;
    }

    public static Boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
