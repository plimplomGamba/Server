package de.plimplom.gamba.server.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
