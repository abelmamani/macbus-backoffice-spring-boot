package ar.edu.undec.adapter.service.auth.config;

import ar.edu.undec.adapter.data.auth.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        final String token = getTokenFromRequest(request);
        final String username;
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }
        username = jwtService.getUsernameFromToken(token);
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest){
        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
            return authHeader.substring(7);
        return null;
    }

    /*private void handleException(HttpServletResponse response, Exception ex) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message;
        if (ex instanceof BadCredentialsException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            message = "Fallo de autenticación";
        } else if (ex instanceof AccessDeniedException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            message = "No autorizado";
        } else if (ex instanceof SignatureException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            message = "Firma JWT no válida";
        } else if (ex instanceof ExpiredJwtException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            message = "El token JWT ya ha expirado";
        } else {
            message = ex.getMessage();
        }
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }*/
}
