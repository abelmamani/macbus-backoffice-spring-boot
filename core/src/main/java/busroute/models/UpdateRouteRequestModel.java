package busroute.models;

import busroute.exceptions.RouteException;

public class UpdateRouteRequestModel {
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;

    private UpdateRouteRequestModel(){}
    private UpdateRouteRequestModel(String shortName, String longName, String description, String color, String textColor) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
    }
    public static UpdateRouteRequestModel getInstance(String shortName, String longName, String description, String color, String textColor) {
        if (shortName == null || shortName.trim().isEmpty())
            throw new RouteException("El nombre corto de la linea es requerido.");
        if (longName == null || longName.trim().isEmpty())
            throw new RouteException("El nombre largo de la linea es requerido.");
        if (color == null || color.trim().isEmpty())
            throw new RouteException("El color de la linea es requerido.");
        if (textColor == null || textColor.trim().isEmpty())
            throw new RouteException("El color de texto de la linea es requerido.");
        return new UpdateRouteRequestModel(shortName, longName, description, color, textColor);
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
}
