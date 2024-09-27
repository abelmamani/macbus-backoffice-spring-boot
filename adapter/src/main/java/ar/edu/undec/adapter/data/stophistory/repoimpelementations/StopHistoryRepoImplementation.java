package ar.edu.undec.adapter.data.stophistory.repoimpelementations;

import ar.edu.undec.adapter.data.stophistory.crud.StopHistoryCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stophistory.models.StopHistoryCountsResponseModel;
import stophistory.outputs.StopHistoryRepository;
import java.util.Collection;

@AllArgsConstructor
@Service
public class StopHistoryRepoImplementation implements StopHistoryRepository {
    private StopHistoryCRUD stopHistoryCRUD;
    @Override
    public Collection<StopHistoryCountsResponseModel> findStopHistoryCounts(String startDate, String endDate) {
        return stopHistoryCRUD.findStopHistoryCounts(startDate, endDate);
    }
}
