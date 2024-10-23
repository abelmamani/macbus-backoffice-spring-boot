package ar.edu.undec.adapter.data.trip.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.busservice.mapper.ServiceDataMapper;
import ar.edu.undec.adapter.data.stoptime.mapper.StopTimeDataMapper;
import ar.edu.undec.adapter.data.trip.models.TripNode;
import ar.edu.undec.adapter.data.tripupdate.mapper.TripUpdateDataMapper;
import trip.models.Trip;
import trip.models.TripResponseModel;
import java.util.Map;
import java.util.stream.Collectors;

public class TripDataMapper {
    public static Trip dataCoreMapper(TripNode trip){
        try {
            return Trip.getInstance(trip.getId(),
                    trip.getDepartureTime(),
                    trip.getTripStatus(),
                    ServiceDataMapper.dataCoreMapper(trip.getService()),
                    TripUpdateDataMapper.dataCoreMapper(trip.getTripUpdate()),
                    trip.getStopTImes().stream().map(StopTimeDataMapper::dataCoreMapper).collect(Collectors.toList()));
            }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static TripNode dataNodeMapper(Trip trip){
        try {
            return TripNode.builder()
                    .id(trip.getId())
                    .departureTime(trip.getDepartureTime())
                    .tripStatus(trip.getTripStatus())
                    .service(ServiceDataMapper.dataNodeMapper(trip.getService()))
                    .tripUpdate(TripUpdateDataMapper.dataNodeMapper(trip.getTripUpdate()))
                    .stopTImes(trip.getStopTImes().stream().map(StopTimeDataMapper::dataNodeMapper).collect(Collectors.toList()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }

    public static TripResponseModel mapToTripResponseModel(Map<String, Object> map) {
        return new TripResponseModel(
                (String) map.get("id"),
                (String) map.get("departureTime"),
                (String) map.get("tripStatus"),
                (String) map.get("service")
        );
    }
}
