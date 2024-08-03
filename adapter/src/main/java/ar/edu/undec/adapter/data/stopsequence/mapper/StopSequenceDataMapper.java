package ar.edu.undec.adapter.data.stopsequence.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import ar.edu.undec.adapter.data.stopsequence.models.StopSequenceNode;
import stopsequence.models.StopSequence;

public class StopSequenceDataMapper {
    public static StopSequence dataCoreMapper(StopSequenceNode stopSequence){
        try {
            return StopSequence.getInstance(stopSequence.getId(),
                    stopSequence.getArrivalTime(),
                    stopSequence.getDistanceTraveled(),
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
                    .stop(StopDataMapper.dataNodeMapper(stopSequence.getStop()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}
