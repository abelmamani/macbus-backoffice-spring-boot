package ar.edu.undec.adapter.service.conductor.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import conductor.inputs.CreateConductorInput;
import conductor.inputs.DeleteConductorInput;
import conductor.inputs.GetConductorsInput;
import conductor.inputs.UpdateConductorInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import user.models.CreateUserRequestModel;
import user.models.UpdateUserRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("conductors")
@CrossOrigin("*")
public class ConductorController {
    private GetConductorsInput getConductorsInput;
    private CreateConductorInput createConductorInput;
    private UpdateConductorInput updateConductorInput;
    private DeleteConductorInput deleteConductorInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getConductors(){
        try {
            return ResponseEntity.ok(getConductorsInput.getConductors());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createConductor(@RequestBody CreateUserRequestModel createUserRequestModel){
        try {
            createConductorInput.createConductor(createUserRequestModel);
            return ResponseManager.response(HttpStatus.CREATED,"Usuario "+createUserRequestModel.getEmail()+" registrado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("email") String email, @RequestBody UpdateUserRequestModel updateUserRequestModel){
        try {
            updateConductorInput.updateConductor(email, updateUserRequestModel);
            return ResponseManager.successRequest("Usuario "+updateUserRequestModel.getEmail()+" actualizado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteConductor(@PathVariable("email") String email){
        try {
            deleteConductorInput.deleteConductor(email);
            return ResponseManager.successRequest("Conductor "+email+" eliminado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}
