package ar.edu.undec.adapter.data.stophistory.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import ar.edu.undec.adapter.data.stophistory.models.StopHistoryNode;
import stophistory.models.StopHistory;
import stophistory.models.StopHistoryCountsResponseModel;
import java.util.Map;

public class StopHistoryDataMapper {
    public static StopHistory dataCoreMapper(StopHistoryNode stopHistoryNode){
        try {
            return StopHistory.getInstance(stopHistoryNode.getId(),
                    stopHistoryNode.getDate(),
                    stopHistoryNode.getTime(),
                    StopDataMapper.dataCoreMapper(stopHistoryNode.getStop()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static StopHistoryNode dataNodeMapper(StopHistory stopHistory){
        try {
            return StopHistoryNode.builder()
                    .id(stopHistory.getId())
                    .date(stopHistory.getDate())
                    .time(stopHistory.getTime())
                    .stop(StopDataMapper.dataNodeMapper(stopHistory.getStop()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
    public static StopHistoryCountsResponseModel mapToStopHistoryCountsResponseModel(Map<String, Object> map) {
        return new StopHistoryCountsResponseModel(
                (String) map.get("stop"),
                (Long) map.get("total")
        );
    }
}