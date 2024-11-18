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
    @PreAuthorize("hasAuthority('STOP_MANAGER')")
    public ResponseEntity<?> getStops(){
        try {
            return ResponseEntity.ok(getStopsInput.getStops());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/active")
    @PreAuthorize("hasAuthority('ROUTE_MANAGER')")
    public ResponseEntity<?> getActiveStops(){
        try {
            return ResponseEntity.ok(getStopsInput.getActiveStops());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('STOP_MANAGER')")
    public ResponseEntity<?> getStop(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(getStopInput.getStop(name));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('STOP_MANAGER')")
    public ResponseEntity<?> createStop(@RequestBody CreateStopRequestModel createRouteRequestModel){
        try {
            return ResponseManager.createdRequest(createStopInput.createStop(createRouteRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('STOP_MANAGER')")
    public ResponseEntity<?> updateStop(@RequestBody UpdateStopRequestModel updateRouteRequestModel){
        try {
            updateStopInput.updateStop(updateRouteRequestModel);
            return ResponseManager.successRequest("Se actualizo la parada correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('STOP_MANAGER')")
    public ResponseEntity<?> deleteStop(@PathVariable("id") String id){
        try {
            deleteStopInput.deleteStop(id);
            return ResponseManager.successRequest("Se elimino la parada correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}