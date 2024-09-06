package ar.edu.undec.adapter.service.busroute.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import busroute.inputs.*;
import busroute.models.CreateRouteRequestModel;
import busroute.models.UpdateRouteRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("routes")
@CrossOrigin("*")
public class RouteController {
    private GetRoutesInput getRoutesInput;
    private GetRouteInput getRouteInput;
    private CreateRouteInput createRouteInput;
    private UpdateGeneralInfoInput updateGeneralInfoInputt;
    private DeleteRouteInput deleteRouteInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRoutes(){
        try {
            return ResponseEntity.ok(getRoutesInput.getRoutes());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRouteByName(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(getRouteInput.getRouteGeneralInfoByName(name));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRoute(@RequestBody CreateRouteRequestModel createRouteRequestModel){
        try {
            return ResponseManager.createdRequest(createRouteInput.createRoute(createRouteRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRoute(@PathVariable("name") String name, @RequestBody UpdateRouteRequestModel updateRouteRequestModel){
        try {
            updateGeneralInfoInputt.updateRoute(name, updateRouteRequestModel);
            return ResponseManager.successRequest("Se actualizo la linea correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRoute(@PathVariable("name") String name){
        try {
            deleteRouteInput.deleteRoute(name);
            return ResponseManager.successRequest("Se elimino la linea correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}