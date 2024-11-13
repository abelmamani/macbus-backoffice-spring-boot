package ar.edu.undec.adapter.service.role.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import privilege.inputs.GetPrivilegesInput;
import role.inputs.CreateRoleInput;
import role.inputs.DeleteRoleInput;
import role.inputs.GetRolesInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import role.inputs.UpdateRoleInput;
import role.models.CreateRoleRequestModel;
import role.models.UpdateRoleRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("roles")
@CrossOrigin("*")
public class RoleController {
    private GetRolesInput getRolesInput;
    private GetPrivilegesInput getPrivilegesInput;
    private CreateRoleInput createRoleInput;
    private UpdateRoleInput updateRoleInput;
    private DeleteRoleInput deleteRoleInput;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER_MANAGER', 'ROLE_MANAGER')")
    public ResponseEntity<?> getRoles(){
        try {
            return ResponseEntity.ok(getRolesInput.getRoles());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
    @GetMapping("/privileges")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> getPrivileges(){
        try {
            return ResponseEntity.ok(getPrivilegesInput.getPrivileges());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> createRole(@RequestBody CreateRoleRequestModel createRoleRequestModel){
        try {
            createRoleInput.createRole(createRoleRequestModel);
            return ResponseManager.response(HttpStatus.CREATED,"Rol "+createRoleRequestModel.getName()+" registrado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> updateRole(@RequestBody UpdateRoleRequestModel updateRoleRequestModel){
        try {
            updateRoleInput.updateRole(updateRoleRequestModel);
            return ResponseManager.successRequest("Rol "+ updateRoleRequestModel.getName()+" actualizado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> deleteRole(@PathVariable("id") String id){
        try {
            deleteRoleInput.deleteRole(id);
            return ResponseManager.successRequest("Rol eliminado correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}
