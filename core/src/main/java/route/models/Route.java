package route.models;

import route.exceptions.RouteException;
import shape.exceptions.ShapeException;
import shape.models.Shape;
import stopsequence.models.StopSequence;
import trip.models.Trip;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Route {
    private Long id;
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;
    private RouteStatus routeStatus;
    private List<Shape> shapes;
    private List<StopSequence> stopSequences;
    private List<Trip> trips;

    private Route(Long id, String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus, List<Shape> shapes, List<StopSequence> stopSequences, List<Trip> trips) {
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

    public static Route getInstance(Long id, String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus, List<Shape> shapes, List<StopSequence> stopSequences, List<Trip> trips) {
        if(shortName == null || shortName.trim().isEmpty())
            throw new RouteException("El nombre corto de la linea es requerido.");
        if(longName == null || longName.trim().isEmpty())
            throw new RouteException("El nombre largo de la linea es requerido.");
        if(color == null || color.trim().isEmpty())
            throw new RouteException("El color de la linea es requerido.");
        if(textColor == null || textColor.trim().isEmpty())
            throw new RouteException("El color de texto de la linea es requerido.");
        if(routeStatus == null)
            throw new RouteException("El estado de la linea es requerido.");
        validateShapes(shapes);
        validateStopSequences(stopSequences);
        validateTrips(trips);
        return new Route(id, shortName, longName, description, color, textColor, routeStatus, shapes, stopSequences, trips);
    }

    public Long getId() {
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

    private static void validateShapes(List<Shape> shapes) {
        if (shapes == null)
            throw new ShapeException("La forma de recorrido de la línea es requerida.");
        if (shapes.size() > 0){
            if(!shapes.stream().anyMatch(shape -> shape.getSequence() == 1 && shape.getDistanceTraveled() == 0))
                throw new ShapeException("El primer shape debe tener una distancia de 0.");
            //IntStream.range(0, shapes.size())
                    //.forEach(i -> {
                      //  validateShapeNotNull(shapes, i);
                       // validateShapeSequence(shapes, i);
                        //validateDistanceTraveled(shapes, i);
                   // });
        }
    }

    private static void validateShapeNotNull(List<Shape> shapes, int i) {
        if (shapes.get(i) == null)
            throw new ShapeException("El shape en la posición " + i + " es nulo.");
    }

    private static void validateShapeSequence(List<Shape> shapes, int i) {
            if (shapes.get(i).getSequence() != i + 1)
                throw new ShapeException("La secuencia del shape en la posición " + i + " es incorrecta. Debería ser " + (i + 1) + ".");
    }
    private static void validateDistanceTraveled(List<Shape> shapes, int i) {
            if (i > 0 && shapes.get(i).getDistanceTraveled() <= shapes.get(i - 1).getDistanceTraveled())
                throw new ShapeException("La distancia recorrida debe ser creciente. Error en la posición " + i + ".");
    }

    private static void validateStopSequences(List<StopSequence> stopSequences) {
        if (stopSequences == null)
            throw new RouteException("La secuencia de paradas de la línea es requerida.");

        if (stopSequences.stream().anyMatch(Objects::isNull))
            throw new RouteException("Una de las secuancias de parada es nulo.");

        for (int i = 0; i < stopSequences.size(); i++) {
            StopSequence stopSequence = stopSequences.get(i);
            if (i > 0) {
                StopSequence previousStopSequence = stopSequences.get(i - 1);
                if (stopSequence.getDistanceTraveled() <= previousStopSequence.getDistanceTraveled()) {
                    throw new RouteException("La distancia recorrida debe ser creciente. Error en la posición " + i + ".");
                }
                if (stopSequence.getArrivalTime().isBefore(previousStopSequence.getArrivalTime())) {
                    throw new RouteException("El tiempo de arribo debe ser creciente. Error en la posición " + i + ".");
                }
            }
        }
    }

    private static void validateTrips(List<Trip> trips) {
        if (trips == null)
            throw new RouteException("Los viajes de la línea son requeridos.");

        if (trips.stream().anyMatch(Objects::isNull))
            throw new RouteException("Uno de los viajes es nulo.");
    }

    public Shape getLastShape() {
        return this.shapes.stream()
                .max(Comparator.comparingInt(Shape::getSequence))
                .orElseThrow(() -> new NoSuchElementException("La shape no esta disponible."));
    }

    public StopSequence getLastStopSequence() {
        return this.stopSequences.stream()
                .max(Comparator.comparingInt(StopSequence::getDistanceTraveled))
                .orElseThrow(() -> new NoSuchElementException("El stop sequence no esta disponible."));
    }
}
