package ar.edu.undec.adapter.service.calendar.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.inputs.*;
import service.models.CreateServiceRequestModel;
import service.models.UpdateServiceRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("services")
@CrossOrigin("*")
public class ServiceController {
    private GetServicesInput getServicesInput;
    private GetServiceInput getServiceInput;
    private CreateServiceInput createServiceInput;
    private UpdateServiceInput updateServiceInput;
    private DeleteServiceInput deleteServiceInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getServices(){
        try {
            return ResponseEntity.ok(getServicesInput.getServices());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getService(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(getServiceInput.getService(id));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createService(@RequestBody CreateServiceRequestModel createServiceRequestModel){
        try {
            return ResponseManager.createRequest(createServiceInput.createService(createServiceRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateService(@RequestBody UpdateServiceRequestModel updateServiceRequestModel){
        try {
            updateServiceInput.updateService(updateServiceRequestModel);
            return ResponseManager.successRequest("Se actualizo el servicio correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
        try {
            deleteServiceInput.deleteService(id);
            return ResponseManager.successRequest("Se elimino eñ servicio correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}