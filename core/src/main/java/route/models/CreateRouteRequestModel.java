package route.models;

import shape.models.Shape;
import java.util.List;

public class CreateRouteRequestModel {
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;

    private CreateRouteRequestModel(){}
    private CreateRouteRequestModel(String shortName, String longName, String description, String color, String textColom) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
    }
    public static CreateRouteRequestModel getInstance(String shortName, String longName, String description, String color, String textColor) {
        return new CreateRouteRequestModel(shortName, longName, description, color, textColor);
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
