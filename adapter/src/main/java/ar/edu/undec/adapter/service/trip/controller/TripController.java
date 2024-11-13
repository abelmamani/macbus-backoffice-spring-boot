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
import tripupdate.inputs.GetTripUpdatesInput;
import tripupdate.inputs.StopTripUpdateInput;

@AllArgsConstructor
@RestController
@RequestMapping("trips")
@CrossOrigin("*")
public class TripController {
    private GetTripsByRouteInput getTripsByRouteInput;
    private CreateTripInput createTripInput;
    private DeleteTripInput deleteTripInput;
    private GetTripUpdatesInput getTripUpdatesInput;
    private StopTripUpdateInput stopTripUpdateInput;

    @GetMapping("/route/{longName}")
    @PreAuthorize("hasAuthority('ROUTE_MANAGER')")
    public ResponseEntity<?> getTripsByRoute(@PathVariable("longName") String longName){
        try {
            return ResponseEntity.ok(getTripsByRouteInput.getAllTrips(longName));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROUTE_MANAGER')")
    public ResponseEntity<?> createTrip(@RequestBody CreateTripRequestModel createTripRequestModel){
        try {
            return ResponseEntity.created(null).body(createTripInput.createTrip(createTripRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
    @DeleteMapping("/route/{longName}/{tripId}")
    @PreAuthorize("hasAuthority('ROUTE_MANAGER')")
    public ResponseEntity<?> deleteStopSequenceByRouteAndArrivalTime(@PathVariable("longName") String longName, @PathVariable("tripId") String tripId){
        try {
            return ResponseEntity.ok(deleteTripInput.deleteTrip(longName, tripId));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TRIP_MONITOR')")
    public ResponseEntity<?> stopTripUpdate(@PathVariable("id") String id){
        try {
            stopTripUpdateInput.stopTripUpdate(id);
            return ResponseManager.successRequest("Viaje detenido correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @GetMapping("/updates")
    @PreAuthorize("hasAuthority('TRIP_MONITOR')")
    public ResponseEntity<?> getTripUpdates(){
        try {
            return ResponseEntity.ok(getTripUpdatesInput.getTripUpdates());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}