package com.Ahmed.AhmedSpring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyCustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
           UserDetails user=(UserDetails) authentication.getPrincipal();
           String name=user.getUsername();
           System.out.println("user : "+name+" is logged in");


        System.out.println("User Authorities:");
        authentication.getAuthorities().forEach(auth -> System.out.println(auth.getAuthority()));

            boolean hasJobSeekerRole=authentication
                    .getAuthorities()
                    .stream()
                    .anyMatch(a -> a.getAuthority().equals("Candidate"));
        boolean hasRecruitRole=authentication
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("Recruiter"));
          if(hasJobSeekerRole || hasRecruitRole){
              response.sendRedirect("/dashboard/");
          }
    }
}
