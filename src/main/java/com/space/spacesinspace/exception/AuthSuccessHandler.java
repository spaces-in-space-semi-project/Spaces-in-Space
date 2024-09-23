package com.space.spacesinspace.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

@Configuration
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Get the logged-in user's roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Redirect based on roles
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ADMIN")) {
                // Redirect to admin dashboard
                response.sendRedirect("/admin/member/selectAllMembers");
                return;
            } else if (authority.getAuthority().equals("USER")) {
                // Redirect to user homepage
                response.sendRedirect("/");
                return;
            }
        }

        // Default fallback if no role matches
        response.sendRedirect("/");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
