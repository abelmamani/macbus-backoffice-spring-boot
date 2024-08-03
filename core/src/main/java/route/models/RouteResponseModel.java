package route.models;

public class RouteResponseModel {
    private Long id;
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;
    private RouteStatus routeStatus;
    private RouteResponseModel(){}

    public RouteResponseModel(Long id, String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
        this.routeStatus = routeStatus;
    }

    public static RouteResponseModel getInstance(Long id, String shortName, String longName, String description, String color, String textColor, RouteStatus routeStatus) {
        return new RouteResponseModel(id, shortName, longName, description, color, textColor, routeStatus);
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
}
