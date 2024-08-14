package route.models;

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
