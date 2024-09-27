package report.models;

public class TripStatusCountsResponseModel {
    private Long total;
    private Long scheduled;
    private Long cancelled;
    private Long running;
    private Long completed;
    public TripStatusCountsResponseModel(){}

    public TripStatusCountsResponseModel(Long total, Long scheduled, Long cancelled, Long running, Long completed) {
        this.total = total;
        this.scheduled = scheduled;
        this.cancelled = cancelled;
        this.running = running;
        this.completed = completed;
    }

    public Long getTotal() {
        return total;
    }

    public Long getScheduled() {
        return scheduled;
    }

    public Long getCancelled() {
        return cancelled;
    }

    public Long getRunning() {
        return running;
    }

    public Long getCompleted() {
        return completed;
    }
}
