package ar.edu.undec.adapter.service.trip.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import trip.inputs.CreateTripInput;
import trip.inputs.DeleteTripInput;
import trip.inputs.GetTripsByRouteInput;
import trip.models.CreateTripRequestModel;

import java.time.LocalTime;

@AllArgsConstructor
@RestController
@RequestMapping("trips")
@CrossOrigin("*")
public class TripController {
    private GetTripsByRouteInput getTripsByRouteInput;
    private CreateTripInput createTripInput;
    private DeleteTripInput deleteTripInput;

    @GetMapping("/route/{longName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTripsByRoute(@PathVariable("longName") String longName){
        try {
            return ResponseEntity.ok(getTripsByRouteInput.getAllTrips(longName));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTrip(@RequestBody CreateTripRequestModel createTripRequestModel){
        try {
            return ResponseEntity.created(null).body(createTripInput.createTrip(createTripRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
    @DeleteMapping("/route/{longName}/{serviceName}/{departureTime}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStopSequenceByRouteAndArrivalTime(@PathVariable("longName") String longName, @PathVariable("serviceName") String serviceName, @PathVariable("departureTime") LocalTime departureTime){
        try {
            return ResponseEntity.ok(deleteTripInput.deleteTrip(longName, serviceName, departureTime));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}