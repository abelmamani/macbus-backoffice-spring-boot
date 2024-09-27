package stophistory.outputs;

import stophistory.models.StopHistoryCountsResponseModel;
import java.util.Collection;

public interface StopHistoryRepository {
    Collection<StopHistoryCountsResponseModel> findStopHistoryCounts(String startDate, String endDate);
}
