package ar.edu.undec.adapter.data.stopsequence.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import audit.EntityStatus;
import stop.models.StopAssignedStatus;
import stopsequence.models.StopResponseModel;
import stopsequence.models.StopSequence;
import stopsequence.models.StopSequenceResponseModel;

import java.util.Map;

public class StopSequenceDataMapper {
    public static StopSequence dataCoreMapper(StopSequenceNode stopSequence){
        try {
            return StopSequence.getInstance(stopSequence.getId(),
                    stopSequence.getArrivalTime(),
                    stopSequence.getDistanceTraveled(),
                    stopSequence.getHeadsign(),
                    StopDataMapper.dataCoreMapper(stopSequence.getStop()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static StopSequenceNode dataNodeMapper(StopSequence stopSequence){
        try {
            return StopSequenceNode.builder()
                    .id(stopSequence.getId())
                    .arrivalTime(stopSequence.getArrivalTime())
                    .distanceTraveled(stopSequence.getDistanceTraveled())
                    .headsign(stopSequence.getHeadsign())
                    .stop(StopDataMapper.dataNodeMapper(stopSequence.getStop()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }

    public static StopSequenceResponseModel mapToStopSequenceResponseModel(Map<String, Object> map) {
        Map<String, Object> stopMap = (Map<String, Object>) map.get("stop");
        StopResponseModel stopResponseModel = new StopResponseModel(
                (String) stopMap.get("id"),
                (String) stopMap.get("name"),
                (Double) stopMap.get("latitude"),
                (Double) stopMap.get("longitude"),
                StopAssignedStatus.valueOf((String) stopMap.get("assignedStatus")),
                EntityStatus.valueOf((String) stopMap.get("status")));;
        return new StopSequenceResponseModel(
                (String) map.get("id"),
                (String) map.get("arrivalTime"),
                (Long) map.get("distanceTraveled"),
                (String) map.get("headsign"),
                stopResponseModel
        );
    }
}
