package ar.edu.undec.adapter.data.stop.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.stop.models.StopNode;
import stop.models.Stop;

public class StopDataMapper {
    public static Stop dataCoreMapper(StopNode stopNode){
        try {
            return Stop.getInstance(stopNode.getId(),
                    stopNode.getName(),
                    stopNode.getLatitude(),
                    stopNode.getLongitude(),
                    stopNode.getStatus());
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static StopNode dataNodeMapper(Stop stop){
        try {
            return StopNode.builder()
                    .id(stop.getId())
                    .name(stop.getName())
                    .latitude(stop.getLatitude())
                    .longitude(stop.getLongitude())
                    .status(stop.getStatus())
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}