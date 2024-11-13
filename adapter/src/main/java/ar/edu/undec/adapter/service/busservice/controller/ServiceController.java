package ar.edu.undec.adapter.service.busservice.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import busservice.inputs.*;
import busservice.models.ServiceModel;

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
    @PreAuthorize("hasAnyAuthority('SERVICE_MANAGER', 'ROUTE_MANAGER', 'CALENDAR_DATE_MANAGER')")
    public ResponseEntity<?> getServices(){
        try {
            return ResponseEntity.ok(getServicesInput.getServices());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('SERVICE_MANAGER')")
    public ResponseEntity<?> getService(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(getServiceInput.getService(name));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SERVICE_MANAGER')")
    public ResponseEntity<?> createService(@RequestBody ServiceModel createServiceRequestModel){
        try {
            return ResponseManager.createdRequest(createServiceInput.createService(createServiceRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasAuthority('SERVICE_MANAGER')")
    public ResponseEntity<?> updateService(@PathVariable("name") String name, @RequestBody ServiceModel updateServiceRequestModel){
        try {
            updateServiceInput.updateService(name, updateServiceRequestModel);
            return ResponseManager.successRequest("Se actualizo el servicio correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('SERVICE_MANAGER')")
    public ResponseEntity<?> deleteService(@PathVariable("name") String name){
        try {
            deleteServiceInput.deleteServiceByName(name);
            return ResponseManager.successRequest("Se elimino eñ servicio correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}