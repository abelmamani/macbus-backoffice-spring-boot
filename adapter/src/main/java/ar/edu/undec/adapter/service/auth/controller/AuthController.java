package ar.edu.undec.adapter.service.auth.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import auth.inputs.LoginInput;
import auth.inputs.RecoverPasswordInput;
import auth.models.LoginRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final LoginInput loginInput;
    private final RecoverPasswordInput recoverPasswordInput;
    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequestModel loginRequestModel){
        try {
            return ResponseEntity.ok(loginInput.login(loginRequestModel));
        }catch (Exception e) {
            return ResponseManager.badRequest(e.getMessage());
        }
    }
    @PostMapping("recover-password")
    public ResponseEntity<?> recoverPassword(@RequestParam String email) {
        try {
            recoverPasswordInput.sendRecoveryEmail(email);
            return ResponseManager.successRequest("Se envio correctamente el email.");
        }catch (Exception e) {
            return ResponseManager.badRequest(e.getMessage());
        }
    }

    @PostMapping("reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            recoverPasswordInput.resetPassword(token, newPassword);
            return ResponseManager.successRequest("Se actualizo la contraseña correctamente.");
        }catch (Exception e) {
            return ResponseManager.badRequest(e.getMessage());
        }
    }
}