package service.models;

import stop.exceptions.StopException;
import java.time.LocalDate;

public class Service {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Service(String id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Service getInstance(String id, String name, LocalDate startDate, LocalDate endDate) {
        if(name == null || name.trim().isEmpty())
            throw new StopException("El nombre del servicio es requerido.");
        if(startDate == null)
            throw new StopException("La fecha de inicio del servicio es requerido.");
        if(endDate == null)
            throw new StopException("La fecha de fin del servicio es requerido.");
        return new Service(id, name, startDate, endDate);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
