package ar.edu.undec.adapter.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseManager {
    public static ResponseEntity<?> response(HttpStatus status, String message){
        Map<String, String> body = new HashMap();
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
    public static ResponseEntity<?> badRequest(String message){
        return response(HttpStatus.BAD_REQUEST, message);
    }
    public static ResponseEntity<?> successRequest(String message){
        return response(HttpStatus.OK, message);
    }
    public static ResponseEntity<?> createdRequest(String id) {
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.created(null).body(response);
    }
}
