package busroute.models;

import audit.EntityStatus;

public class RouteGeneralInfoResponseModel {
    private String shortName;
    private String longName;
    private String description;
    private String color;
    private String textColor;
    private RouteProgressStatus progressStatus;
    private EntityStatus status;

    public RouteGeneralInfoResponseModel() {}

    public RouteGeneralInfoResponseModel(String shortName, String longName, String description, String color, String textColor, RouteProgressStatus progressStatus, EntityStatus status) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.color = color;
        this.textColor = textColor;
        this.progressStatus = progressStatus;
        this.status = status;
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

    public RouteProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public EntityStatus getStatus() {
        return status;
    }
}
