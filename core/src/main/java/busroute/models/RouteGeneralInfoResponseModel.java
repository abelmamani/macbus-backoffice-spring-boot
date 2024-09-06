package busroute.models;

public class RouteGeneralInfoResponseModel {
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;
    private RouteStatus routeStatus;
    public RouteGeneralInfoResponseModel(){}

    public RouteGeneralInfoResponseModel(String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
        this.routeStatus = routeStatus;
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
}
