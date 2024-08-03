package service.models;

import java.time.LocalDate;

public class UpdateServiceRequestModel {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private UpdateServiceRequestModel(){}
    private UpdateServiceRequestModel(Long id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public static UpdateServiceRequestModel getInstance(Long id, String name, LocalDate startDate, LocalDate endDate) {
        return new UpdateServiceRequestModel(id, name, startDate, endDate);
    }

    public Long getId() {
        return id;
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
