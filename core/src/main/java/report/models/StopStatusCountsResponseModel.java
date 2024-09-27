package report.models;

public class StopStatusCountsResponseModel {
    private Long total;
    private Long unassigned;
    private Long assigned;
    public StopStatusCountsResponseModel(){}

    public StopStatusCountsResponseModel(Long total, Long unassigned, Long assigned) {
        this.total = total;
        this.unassigned = unassigned;
        this.assigned = assigned;
    }

    public Long getTotal() {
        return total;
    }

    public Long getUnassigned() {
        return unassigned;
    }

    public Long getAssigned() {
        return assigned;
    }
}
