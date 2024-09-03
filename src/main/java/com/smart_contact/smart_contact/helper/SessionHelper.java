package com.smart_contact.smart_contact.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {
    public static void removeSession() {

        try {
            System.out.println("Removing message fro session...");
            HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getSession();
            httpSession.removeAttribute("message");

        } catch (Exception e) {
            System.out.println("Error in session helper" + e);
            e.printStackTrace();
        }
    }
}