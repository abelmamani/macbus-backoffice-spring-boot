package ar.edu.undec.adapter.service.stop.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stop.inputs.*;
import stop.models.CreateStopRequestModel;
import stop.models.UpdateStopRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("stops")
@CrossOrigin("*")
public class StopController {
    private GetStopsInput getStopsInput;
    private GetStopInput getStopInput;
    private CreateStopInput createStopInput;
    private UpdateStopInput updateStopInput;
    private DeleteStopInput deleteStopInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStops(){
        try {
            return ResponseEntity.ok(getStopsInput.getStops());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStop(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(getStopInput.getStop(name));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createStop(@RequestBody CreateStopRequestModel createRouteRequestModel){
        try {
            return ResponseManager.createdRequest(createStopInput.createStop(createRouteRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStop(@PathVariable("name") String name, @RequestBody UpdateStopRequestModel updateRouteRequestModel){
        try {
            updateStopInput.updateStop(name, updateRouteRequestModel);
            return ResponseManager.successRequest("Se actualizo la parada correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStop(@PathVariable("name") String name){
        try {
            deleteStopInput.deleteStop(name);
            return ResponseManager.successRequest("Se elimino la parada correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}