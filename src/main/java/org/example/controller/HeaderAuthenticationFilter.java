package org.example.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class HeaderAuthenticationFilter extends GenericFilterBean {

    private static final String HEADER_CLIENT_ID = "forecast9.p.rapidapi.com";
    private static final String HEADER_CLIENT_SECRET = "d4a910d418mshc6da816c9a51540p1575f5jsnb083c4e9a8a6";

    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse, jakarta.servlet.FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String clientId = httpRequest.getHeader("clientId");
        String clientSecret = httpRequest.getHeader("clientSecret");

        if (clientId != null && clientSecret != null) {
            boolean isValid = validateClientCredentials(clientId, clientSecret);
            if(isValid) {
                Authentication authentication = new PreAuthenticatedAuthenticationToken(clientId, clientSecret);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Invalid client ID or client secret");
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Client ID and Client Secret headers are required");
        }

    }

    private boolean validateClientCredentials(String clientId, String clientSecret) {
        if(clientId.equals(HEADER_CLIENT_ID) && clientSecret.equals(HEADER_CLIENT_SECRET)){
            return true;
        }
        return false;

    }
}
