package busroute.models;

import audit.EntityStatus;
import busroute.exceptions.RouteException;
import shape.models.Shape;
import stopsequence.models.StopSequence;
import trip.models.Trip;
import utils.ValidatorUtils;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Route {
    private String id;
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;
    private RouteProgressStatus progressStatus;
    private EntityStatus status;
    private List<Shape> shapes;
    private List<StopSequence> stopSequences;
    private List<Trip> trips;

    private Route(String id, String shortName, String longName, String description, String color, String textColor, RouteProgressStatus progressStatus, EntityStatus status, List<Shape> shapes, List<StopSequence> stopSequences, List<Trip> trips) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
        this.progressStatus = progressStatus;
        this.status = status;
        this.shapes = shapes;
        this.stopSequences = stopSequences;
        this.trips = trips;
    }

    public static Route getInstance(String id, String shortName, String longName, String description, String color, String textColor, RouteProgressStatus progressStatus, EntityStatus status, List<Shape> shapes, List<StopSequence> stopSequences, List<Trip> trips) {
        if (shortName == null || shortName.trim().isEmpty())
            throw new RouteException("El nombre corto de la linea es requerido.");
        if (longName == null || longName.trim().isEmpty())
            throw new RouteException("El nombre largo de la linea es requerido.");
        if (description == null || description.trim().isEmpty())
            throw new RouteException("La descripción de la linea es requerida.");
        if (color == null || color.trim().isEmpty())
            throw new RouteException("El color de la línea es requerido.");
        if (!ValidatorUtils.isValidHexColor(color))
            throw new RouteException("El color de la línea debe estar en formato hexadecimal (# seguido de 6 caracteres hexadecimales).");
        if (textColor == null || textColor.trim().isEmpty())
            throw new RouteException("El color de texto de la línea es requerido.");
        if (!ValidatorUtils.isValidHexColor(textColor))
            throw new RouteException("El color de texto debe estar en formato hexadecimal (# seguido de 6 caracteres hexadecimales).");
        if (progressStatus == null)
            throw new RouteException("El estado de progreso de la linea es requerido.");
        if (status == null)
            throw new RouteException("El estado de la linea es requerido.");
        return new Route(id, shortName.trim(), longName.trim(), description.trim(), color.trim(), textColor.trim(), progressStatus, status, shapes, stopSequences, trips);
    }

    public String getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public String getTextColor() {
        return textColor;
    }

    public RouteProgressStatus getProgressStatus() { return progressStatus; }

    public EntityStatus getStatus() { return status; }

    public List<Shape> getShapes() {
        return shapes;
    }

    public List<StopSequence> getStopSequences() {
        return stopSequences;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Shape getLastShape() {
        return this.shapes.stream()
                .max(Comparator.comparingLong(Shape::getSequence))
                .orElseThrow(() -> new NoSuchElementException("La shape no esta disponible."));
    }

    public StopSequence getStopSequenceByArrivalTime(String arrivalTime) {
        return stopSequences.stream().filter(s -> s.getArrivalTime().equals(arrivalTime)).findFirst().get();
    }

    public StopSequence getLastStopSequence() {
        return this.stopSequences.stream()
                .max(Comparator.comparingLong(StopSequence::getDistanceTraveled))
                .orElseThrow(() -> new NoSuchElementException("El stop sequence no esta disponible."));
    }

    public boolean existStopSequenceByArrivalTime(String arrivalTime) {
        return this.stopSequences.stream().anyMatch(stopSequence -> stopSequence.getArrivalTime().equals(arrivalTime));
    }

    public boolean existStopSequenceByDistanceTraveled(Long distanceTraveled) {
        return this.stopSequences.stream().anyMatch(stopSequence -> stopSequence.getDistanceTraveled().equals(distanceTraveled));
    }

    public boolean existStopSequenceByStopName(String name) {
        return this.stopSequences.stream().anyMatch(stopSequence -> stopSequence.getStop().getName().equals(name));
    }

    public boolean existTripByDepartureTimeAndService(String departureTime, String service) {
        return this.trips.stream().anyMatch(trip -> trip.getDepartureTime().equals(departureTime) && trip.getService().getName().equals(service));
    }

    public Trip getTripByDepartureTimeAndService(String departureTime, String serviceName) {
        return trips.stream().filter(t -> t.getDepartureTime().equals(departureTime) && t.getService().getName().equals(serviceName)).findFirst().get();
    }
}