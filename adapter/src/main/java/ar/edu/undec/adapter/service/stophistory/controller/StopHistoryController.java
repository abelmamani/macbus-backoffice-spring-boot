package ar.edu.undec.adapter.service.stophistory.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stophistory.inputs.GetStopHistoryCountsInput;

@AllArgsConstructor
@RestController
@RequestMapping("stop_histories")
@CrossOrigin("*")
public class StopHistoryController {
    private GetStopHistoryCountsInput getStopHistoryCountsInput;

    @GetMapping("/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStopHistoryCounts(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
        try {
            return ResponseEntity.ok(getStopHistoryCountsInput.getStopHistoryCounts(startDate, endDate));
        }catch (RuntimeException exception){
           return ResponseManager.badRequest(exception.getMessage());
        }
    }

}