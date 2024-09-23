package ar.edu.undec.adapter.service.user.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import user.inputs.*;
import user.models.ChangePasswordRequestModel;
import user.models.CreateUserRequestModel;
import user.models.UpdateUserRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UserController {
    private GetUsersInput getUsersInput;
    private GetUserInput getUserInput;
    private CreateUserInput createUserInput;
    private UpdateUserInput updateUserInput;
    private ChangePasswordInput changePasswordInput;
    private DeleteUserInput deleteUserInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(){
        try {
            return ResponseEntity.ok(getUsersInput.getAllUsers());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable("email") String email){
        try {
            return ResponseEntity.ok(getUserInput.getUser(email));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestModel createUserRequestModel){
        try {
            return ResponseManager.createdRequest(createUserInput.createUser(createUserRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("email") String email, @RequestBody UpdateUserRequestModel updateUserRequestModel){
        try {
            updateUserInput.updateUser(email, updateUserRequestModel);
            return ResponseManager.successRequest("Usuario actualizado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestModel changePasswordRequestModel){
        try {
            changePasswordInput.changePassword(changePasswordRequestModel);
            return ResponseManager.successRequest("Contraseña actualizada correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email){
        try {
            deleteUserInput.deleteUser(email);
            return ResponseManager.successRequest("Usuario eliminado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}
