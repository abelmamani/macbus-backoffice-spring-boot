package busservice.models;

public class ServiceModel {
    private String name;
    private String startDate;
    private String endDate;

    private ServiceModel() {
    }

    public ServiceModel(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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
}