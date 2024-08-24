package stopsequence.models;

import stop.models.Stop;
import stopsequence.exceptions.StopSequenceException;

public class StopSequence {
    private String id;
    private String arrivalTime;
    private Long distanceTraveled;
    private String headsign;
    private Stop stop;

    private StopSequence(String id, String arrivalTime, Long distanceTraveled, String headsign, Stop stop) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.headsign = headsign;
        this.stop = stop;
    }

    public static StopSequence getInstance(String id, String arrivalTime, Long distanceTraveled, String headsign, Stop stop) {
        if(arrivalTime == null)
            throw new StopSequenceException("El tiempo de arribo es requerido.");
        if(distanceTraveled < 0)
            throw new StopSequenceException("La distancia recorrida no puede ser negativo.");
        //if(stop == null)
          //  throw new StopSequenceException("La parada de arribo es requerido.");
        return new StopSequence(id, arrivalTime, distanceTraveled, headsign, stop);
    }

    public String getId() {
        return id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Long getDistanceTraveled() {
        return distanceTraveled;
    }

    public String getHeadsign() { return headsign; }

    public Stop getStop() {
        return stop;
    }
}
