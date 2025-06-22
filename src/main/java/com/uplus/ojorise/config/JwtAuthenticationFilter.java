package com.uplus.ojorise.config;

import com.uplus.ojorise.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        //authorization 제외
        if (path.startsWith("/ojoRise/v3/api-docs")
                || path.startsWith("/ojoRise/swagger-ui")
                || path.startsWith("/ojoRise/swagger-resources")
                || path.startsWith("/ojoRise/webjars")
                || path.equals("/ojoRise/swagger-ui.html")
                || path.equals("/ojoRise/google/ocr")
                || path.startsWith("/ojoRise/plan")
                || path.equals("/ojoRise/myPlan/guest")
                || path.equals("/ojoRise/survey/plan")
                || path.contains("favicon")
                || path.startsWith("/ojoRise/error")
                || path.equals("/ojoRise/auth/refresh")
                || path.startsWith("/ojoRise/auth/kakao")) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Authorization header");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        Long userId = jwtUtil.getUserIdFromToken(token);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(authentication); //사용자 정보

        httpRequest.setAttribute("accessToken", token); //accessToken

        chain.doFilter(request, response);
    }
}