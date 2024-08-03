package stoptime.models;

import stop.models.Stop;
import stoptime.exceptions.StopTimeException;

import java.time.LocalTime;

public class StopTIme {
    private Long id;
    private LocalTime arrivalTime;
    private Integer distanceTraveled;
    private Stop stop;

    public StopTIme(Long id, LocalTime arrivalTime, Integer distanceTraveled, Stop stop) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.stop = stop;
    }

    public static StopTIme getInstance(Long id, LocalTime arrivalTime, Integer distanceTraveled, Stop stop) {
        if(arrivalTime == null)
            throw new StopTimeException("El timepo de arribo no debe ser negativo.");
        if(distanceTraveled < 0)
            throw new StopTimeException("La distancia recorrida no debe ser negativa.");
        if(stop == null)
            throw new StopTimeException("La parada de arribo es requerido.");
        return new StopTIme(id, arrivalTime, distanceTraveled, stop);
    }

    public Long getId() {
        return id;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getDistanceTraveled() {
        return distanceTraveled;
    }

    public Stop getStop() {
        return stop;
    }
}
