package route.models;

import route.exceptions.RouteException;
import shape.models.Shape;
import stopsequence.models.StopSequence;
import trip.models.Trip;
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
    private RouteStatus routeStatus;
    private List<Shape> shapes;
    private List<StopSequence> stopSequences;
    private List<Trip> trips;

    private Route(String id, String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus, List<Shape> shapes, List<StopSequence> stopSequences, List<Trip> trips) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
        this.routeStatus = routeStatus;
        this.shapes = shapes;
        this.stopSequences = stopSequences;
        this.trips = trips;
    }

    public static Route getInstance(String id, String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus, List<Shape> shapes, List<StopSequence> stopSequences, List<Trip> trips) {
        if (shortName == null || shortName.trim().isEmpty())
            throw new RouteException("El nombre corto de la linea es requerido.");
        if (longName == null || longName.trim().isEmpty())
            throw new RouteException("El nombre largo de la linea es requerido.");
        if (color == null || color.trim().isEmpty())
            throw new RouteException("El color de la linea es requerido.");
        if (textColor == null || textColor.trim().isEmpty())
            throw new RouteException("El color de texto de la linea es requerido.");
        if (routeStatus == null)
            throw new RouteException("El estado de la linea es requerido.");
        return new Route(id, shortName, longName, description, color, textColor, routeStatus, shapes, stopSequences, trips);
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

    public RouteStatus getRouteStatus() {
        return routeStatus;
    }

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