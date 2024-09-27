package stophistory.usecases;

import stophistory.exceptions.StopHistoryException;
import stophistory.inputs.GetStopHistoryCountsInput;
import stophistory.models.StopHistoryCountsResponseModel;
import stophistory.outputs.StopHistoryRepository;
import utils.DateUtils;
import java.util.Collection;

public class GetStopHistoryCountsUseCase implements GetStopHistoryCountsInput {
    private StopHistoryRepository stopHistoryRepository;

    public GetStopHistoryCountsUseCase(StopHistoryRepository stopHistoryRepository) {
        this.stopHistoryRepository = stopHistoryRepository;
    }

    @Override
    public Collection<StopHistoryCountsResponseModel> getStopHistoryCounts(String startDate, String endDate) {
        if(!DateUtils.isValidDate(startDate))
            throw new StopHistoryException("La fecha de inicio es invalido.");
        if(!DateUtils.isValidDate(endDate))
            throw new StopHistoryException("La fecha de fin es invalido.");
        return stopHistoryRepository.findStopHistoryCounts(startDate, endDate);
    }
}
