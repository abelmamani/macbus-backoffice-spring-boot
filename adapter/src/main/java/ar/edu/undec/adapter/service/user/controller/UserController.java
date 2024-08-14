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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(getUserInput.getUser(id));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestModel createUserRequestModel){
        try {
            return ResponseManager.createRequest(createUserInput.createUser(createUserRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestModel updateUserRequestModel){
        try {
            return ResponseManager.createRequest(updateUserInput.updateUser(updateUserRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestModel changePasswordRequestModel){
        try {
            changePasswordInput.changePassword(changePasswordRequestModel);
            return ResponseManager.successRequest("Se actualizo correctamente la contraseña.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        try {
            deleteUserInput.deleteUser(id);
            return ResponseManager.successRequest("Se elimino el usuario correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}
