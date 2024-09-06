package ar.edu.undec.adapter.data.stoptime.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.stop.mapper.StopDataMapper;
import ar.edu.undec.adapter.data.stoptime.models.StopTimeNode;
import stoptime.models.StopTIme;

public class StopTimeDataMapper {
    public static StopTIme dataCoreMapper(StopTimeNode stopTime){
        try {
            return StopTIme.getInstance(stopTime.getId(),
                    stopTime.getArrivalTime(),
                    stopTime.getDistanceTraveled(),
                    stopTime.getHeadsign(),
                    StopDataMapper.dataCoreMapper(stopTime.getStop()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static StopTimeNode dataNodeMapper(StopTIme stopTime){
        try {
            return StopTimeNode.builder()
                    .id(stopTime.getId())
                    .arrivalTime(stopTime.getArrivalTime())
                    .distanceTraveled(stopTime.getDistanceTraveled())
                    .headsign(stopTime.getHeadsign())
                    .stop(StopDataMapper.dataNodeMapper(stopTime.getStop()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}
