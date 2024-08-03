package ar.edu.undec.adapter.service.shape.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shape.inpúts.CreateShapeInput;
import shape.inpúts.GetShapesByRouteInput;
import shape.models.CreateShapeRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("shapes")
@CrossOrigin("*")
public class ShapeController {
    private GetShapesByRouteInput getShapesByRouteInput;
    private CreateShapeInput createShapeInput;

    @GetMapping("/route/{longName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getShapesByRoute(@PathVariable("longName") String longName){
        try {
            return ResponseEntity.ok(getShapesByRouteInput.getAllShapes(longName));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createShapes(@RequestBody CreateShapeRequestModel createShapeRequestModel){
        try {
            return ResponseEntity.created(null).body(createShapeInput.createShape(createShapeRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}