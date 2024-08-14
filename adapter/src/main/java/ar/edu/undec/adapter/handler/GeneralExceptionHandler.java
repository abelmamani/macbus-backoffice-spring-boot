package ar.edu.undec.adapter.handler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return buildErrorResponse("Acceso no autorizado", HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    protected ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return buildErrorResponse("El token JWT ha expirado", HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    protected ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        return buildErrorResponse("Token JWT malformado", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {SignatureException.class})
    protected ResponseEntity<Object> handleSignatureException(SignatureException ex, WebRequest request) {
        return buildErrorResponse("Firma JWT no válida", HttpStatus.FORBIDDEN, request);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return handleExceptionInternal(null, body, new HttpHeaders(), status, request);
    }
}