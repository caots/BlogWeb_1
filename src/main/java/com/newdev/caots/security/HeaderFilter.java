package com.newdev.caots.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Xác thực qua token
public class HeaderFilter extends BasicAuthenticationFilter {

    public HeaderFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    // Test Header
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_SECRET);
        if (header == null) {
            chain.doFilter(request, response);
        } else if (header.equals(SecurityConstants.PASSWORD_API)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("client", null, null);
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}