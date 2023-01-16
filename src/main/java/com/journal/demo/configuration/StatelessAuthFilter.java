package com.journal.demo.configuration;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class StatelessAuthFilter extends GenericFilterBean {

    private final TokenAuthService tokenAuthService;

    StatelessAuthFilter(@NonNull TokenAuthService tokenAuthService) {
        this.tokenAuthService = tokenAuthService;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        tokenAuthService.getAuthentication(
                                (HttpServletRequest) servletRequest
                        ).orElse(null)
                );
        filterChain
                .doFilter(
                        servletRequest,
                        servletResponse
                );
    }
}
