package ar.edu.undec.adapter.service.calendardate.controller;

import ar.edu.undec.adapter.utils.ResponseManager;
import calendardate.inputs.CreateCalendarDateInput;
import calendardate.inputs.DeleteCalendarDateInput;
import calendardate.inputs.GetCalendarDatesByServiceInput;
import calendardate.models.CalendarDateModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("calendar_dates")
@CrossOrigin("*")
public class CalendarDateController {
    private GetCalendarDatesByServiceInput getCalendarDatesByServiceInput;
    private CreateCalendarDateInput createCalendarDateInput;
    private DeleteCalendarDateInput deleteCalendarDateInput;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCalendarDates(){
        try {
            return ResponseEntity.ok(getCalendarDatesByServiceInput.getAllCalendarDates());
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCalendarDate(@RequestBody CalendarDateModel createCalendarDateRequestModel){
        try {
            createCalendarDateInput.createCalendarDate(createCalendarDateRequestModel);
            return ResponseManager.successRequest("Fecha de calendario registrado exitosamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }

    @DeleteMapping("/{date}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCalendarDate(@PathVariable("date") String date){
        try {
            deleteCalendarDateInput.deleteCalendarDate(date);
            return ResponseManager.successRequest("Se elimino la fecha de calendario correctamente.");
        }catch (RuntimeException exception){
            return ResponseManager.badRequest(exception.getMessage());
        }
    }
}