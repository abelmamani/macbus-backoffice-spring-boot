package stophistory.models;


public class StopHistoryCountsResponseModel {
    private String name;
    private Long total;
    private StopHistoryCountsResponseModel(){}

    public StopHistoryCountsResponseModel(String name, Long total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public Long getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
