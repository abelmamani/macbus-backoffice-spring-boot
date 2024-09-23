package ar.edu.undec.adapter.service.stopsequene.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stopsequence.inputs.CreateStopSequenceInput;
import stopsequence.inputs.DeleteStopSequenceInput;
import stopsequence.inputs.GetStopSequencesByRouteInput;
import stopsequence.models.CreateStopSequenceRequestModel;

@AllArgsConstructor
@RestController
@RequestMapping("stop_sequences")
@CrossOrigin("*")
public class StopSequenceController {
    private GetStopSequencesByRouteInput getStopSequencesByRouteInput;
    private CreateStopSequenceInput createStopSequenceInput;
    private DeleteStopSequenceInput deleteStopSequenceInput;

    @GetMapping("/route/{longName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStopSequencesByRoute(@PathVariable("longName") String longName){
        try {
            return ResponseEntity.ok(getStopSequencesByRouteInput.getAllStopSequences(longName));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createStopSequence(@RequestBody CreateStopSequenceRequestModel createStopSequenceRequestModel){
        try {
            return ResponseEntity.created(null).body(createStopSequenceInput.createStopSequence(createStopSequenceRequestModel));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/route/{routeName}/{sequenceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStopSequenceByRoute(@PathVariable("routeName") String routeName, @PathVariable("sequenceId") String sequenceId){
        try {
            return ResponseEntity.ok(deleteStopSequenceInput.deleteStopSequence(routeName, sequenceId));
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}