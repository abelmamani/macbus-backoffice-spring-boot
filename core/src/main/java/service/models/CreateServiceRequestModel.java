package service.models;

import java.time.LocalDate;

public class CreateServiceRequestModel {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    private CreateServiceRequestModel() {
    }

    private CreateServiceRequestModel(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static CreateServiceRequestModel getInstance(String name, LocalDate startDate, LocalDate endDate) {
        return new CreateServiceRequestModel(name, startDate, endDate);
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