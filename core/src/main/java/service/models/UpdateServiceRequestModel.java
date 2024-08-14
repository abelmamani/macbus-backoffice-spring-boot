package service.models;

import java.time.LocalDate;

public class UpdateServiceRequestModel {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private UpdateServiceRequestModel(){}
    private UpdateServiceRequestModel(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public static UpdateServiceRequestModel getInstance(String name, LocalDate startDate, LocalDate endDate) {
        return new UpdateServiceRequestModel(name, startDate, endDate);
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
