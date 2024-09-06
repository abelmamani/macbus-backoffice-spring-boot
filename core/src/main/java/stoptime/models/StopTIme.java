package stoptime.models;

import stop.models.Stop;
import stoptime.exceptions.StopTimeException;

public class StopTIme {
    private String id;
    private String arrivalTime;
    private Long distanceTraveled;
    private String headsign;
    private Stop stop;

    public StopTIme(String id, String arrivalTime, Long distanceTraveled, String headsign, Stop stop) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.distanceTraveled = distanceTraveled;
        this.headsign = headsign;
        this.stop = stop;
    }

    public static StopTIme getInstance(String id, String arrivalTime, Long distanceTraveled, String headsign, Stop stop) {
        if(arrivalTime == null)
            throw new StopTimeException("El timepo de arribo no debe ser negativo.");
        if(distanceTraveled < 0)
            throw new StopTimeException("La distancia recorrida no debe ser negativa.");
        if(headsign == null || headsign.trim().isEmpty())
            throw new StopTimeException("El destino es requerido.");
        if(stop == null)
            throw new StopTimeException("La parada de arribo es requerido.");
        return new StopTIme(id, arrivalTime, distanceTraveled, headsign, stop);
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
