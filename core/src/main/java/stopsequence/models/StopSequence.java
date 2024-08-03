package stopsequence.models;

import stop.models.Stop;
import stopsequence.exceptions.StopSequenceException;

import java.time.LocalTime;

public class StopSequence {
    private Long id;
    private LocalTime arrivalTime;
    private Integer distanceTraveled;
    private Stop stop;

    private StopSequence(Long id, LocalTime arrivalTime, Integer distanceTraveled, Stop stop) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.stop = stop;
    }

    public static StopSequence getInstance(Long id, LocalTime arrivalTime, Integer distanceTraveled, Stop stop) {
        if(arrivalTime == null)
            throw new StopSequenceException("El tiempo de arribo es requerido.");
        if(distanceTraveled < 0)
            throw new StopSequenceException("La distancia recorrida no puede ser negativo.");
        if(stop == null)
            throw new StopSequenceException("La parada de arribo es requerido.");
        return new StopSequence(id, arrivalTime, distanceTraveled, stop);
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
