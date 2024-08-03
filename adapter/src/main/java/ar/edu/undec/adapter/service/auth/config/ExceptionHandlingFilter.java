package ar.edu.undec.adapter.service.auth.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            handleException(response, ex);
        }
    }

    private void handleException(HttpServletResponse response, Exception ex) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message;
       if (ex instanceof SignatureException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            message = "Token invalido";
        } else if (ex instanceof ExpiredJwtException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            message = "Sesion expirada";
        } else {
            message = ex.getMessage();
        }
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }
}
