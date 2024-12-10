//package com.sash.volleyballApp.config;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        // Check if the user is an admin
//        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
//            response.sendRedirect("/admin/dashboard");
//        }
//        // Check if the user is a regular user
//        else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
//            response.sendRedirect("/user/dashboard");
//        } else {
//            // For other cases, redirect to a public home page or a default page
//            response.sendRedirect("/");
//        }
//    }
//}
