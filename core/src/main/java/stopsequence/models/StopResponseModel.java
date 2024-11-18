package stopsequence.models;

import audit.EntityStatus;
import stop.models.StopAssignedStatus;

public class StopResponseModel {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private StopAssignedStatus assignedStatus;
    private EntityStatus status;

    public StopResponseModel(){}

    public StopResponseModel(String id, String name, Double latitude, Double longitude, StopAssignedStatus assignedStatus, EntityStatus status) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.assignedStatus = assignedStatus;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public StopAssignedStatus getAssignedStatus() {
        return assignedStatus;
    }

    public EntityStatus getStatus() {
        return status;
    }
}
