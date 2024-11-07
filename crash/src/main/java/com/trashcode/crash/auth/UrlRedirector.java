package com.trashcode.crash.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trashcode.crash.userServices.getcurrentRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UrlRedirector extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private getcurrentRole gRole;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        String redirectURL = request.getContextPath();

        System.out.println("URL REDIRECTOR ROLE :" + gRole.getRole());

        if (gRole.getRole().equals("USER")) {

            System.out.println("USERRR :" + gRole.getRole());
            redirectURL += "/";
        }
        if (gRole.getRole().equals("SELLER")) {
            System.out.println("INSIDE SELLER :" + gRole.getRole());

            redirectURL += "/Seller/";
        }
        if (gRole.getRole().equals("ADMIN")) {
            System.out.println("INSIDE SELLER :" + gRole.getRole());

            redirectURL += "/a/";
        }

        System.out.println("REDIRECT URL IS :::::::::::::::" + redirectURL);

        response.sendRedirect(redirectURL);

    }

}
