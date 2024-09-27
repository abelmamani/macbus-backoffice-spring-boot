package stophistory.inputs;

import stophistory.models.StopHistoryCountsResponseModel;
import java.util.Collection;
public interface GetStopHistoryCountsInput {
    Collection<StopHistoryCountsResponseModel> getStopHistoryCounts(String startDate, String endDate);
}
