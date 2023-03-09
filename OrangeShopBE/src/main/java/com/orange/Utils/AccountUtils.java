package com.orange.Utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class AccountUtils {
    public static String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
