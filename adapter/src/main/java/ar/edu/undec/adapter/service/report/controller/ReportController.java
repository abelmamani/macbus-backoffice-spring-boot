package ar.edu.undec.adapter.service.stophistory.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import report.inputs.GetStatusCountsInput;

@AllArgsConstructor
@RestController
@RequestMapping("reports")
@CrossOrigin("*")
public class ReportController {
    private GetStatusCountsInput getStatusCountsInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStatusCounts(){
        try {
            return ResponseEntity.ok(getStatusCountsInput.getStatusCounts());
        }catch (RuntimeException exception){
            System.out.println(exception.getMessage());
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

}