package ru.project.my.eventnotificator.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.project.my.eventnotificator.security.CustomAuthenticationEntryPoint;

import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class.getName());

    private final JwtTokenManager jwtTokenManager;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public JwtTokenFilter(JwtTokenManager jwtTokenManager, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtTokenManager = jwtTokenManager;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }

        Long userId;
        String role;
        try {
            String jwt = authHeader.substring(7);
            Pair<Long, String> loginAndRole = jwtTokenManager.getUserIdAndRole(jwt);
            userId = loginAndRole.getFirst();
            role = loginAndRole.getSecond();
        } catch (Exception e) {
            log.warn("Jwt токен не валидный", e);
            customAuthenticationEntryPoint.commence(request, response, new InsufficientAuthenticationException(e.getMessage(), e));
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority(role)));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
