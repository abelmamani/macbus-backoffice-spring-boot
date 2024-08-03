package ar.edu.undec.adapter.service.auth.controller;

import auth.inputs.LoginInput;
import auth.models.LoginRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final LoginInput loginInput;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequestModel loginRequestModel){
        try {
            return ResponseEntity.ok(loginInput.login(loginRequestModel));
        }catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}