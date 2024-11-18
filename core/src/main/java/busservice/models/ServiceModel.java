package busservice.models;

public class ServiceModel {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String status;

    private ServiceModel(){}

    public ServiceModel(String id, String name, String startDate, String endDate, String status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }
}