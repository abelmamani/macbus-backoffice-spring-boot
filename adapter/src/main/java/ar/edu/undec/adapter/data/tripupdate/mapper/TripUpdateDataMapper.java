package ar.edu.undec.adapter.data.tripupdate.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.tripupdate.models.TripUpdateNode;
import tripupdate.models.TripUpdate;
import tripupdate.models.TripUpdateResponseModel;
import java.util.Map;

public class TripUpdateDataMapper {
    public static TripUpdate dataCoreMapper(TripUpdateNode tripUpdate){
        try {
            return TripUpdate.getInstance(tripUpdate.getId(),
                    tripUpdate.getLatitude(),
                    tripUpdate.getLongitude(),
                    tripUpdate.getTimestamp());
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static TripUpdateNode dataNodeMapper(TripUpdate tripUpdate){
        try {
            return TripUpdateNode.builder()
                    .id(tripUpdate.getId())
                    .latitude(tripUpdate.getLatitude())
                    .longitude(tripUpdate.getLongitude())
                    .timestamp(tripUpdate.getTimestamp())
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }

    public static TripUpdateResponseModel mapToTripUpdateResponseModel(Map<String, Object> map) {
        return new TripUpdateResponseModel(
                (String) map.get("id"),
                (Double) map.get("latitude"),
                (Double) map.get("longitude"),
                (Long) map.get("timestamp"),
                (String) map.get("route")
        );
    }
}
