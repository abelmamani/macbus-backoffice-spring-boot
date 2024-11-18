package ar.edu.undec.adapter.data.busroute.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.busroute.models.RouteNode;
import ar.edu.undec.adapter.data.shape.mapper.ShapeDataMapper;
import ar.edu.undec.adapter.data.stopsequence.mapper.StopSequenceDataMapper;
import ar.edu.undec.adapter.data.trip.mapper.TripDataMapper;
import audit.EntityStatus;
import busroute.models.Route;
import busroute.models.RouteGeneralInfoResponseModel;
import busroute.models.RouteProgressStatus;

import java.util.Map;
import java.util.stream.Collectors;

public class RouteDataMapper {
    public static Route dataCoreMapper(RouteNode routeNode){
        try {
            return Route.getInstance(routeNode.getId(),
                    routeNode.getShortName(),
                    routeNode.getLongName(),
                    routeNode.getDescription(),
                    routeNode.getColor(),
                    routeNode.getTextColor(),
                    routeNode.getProgressStatus(),
                    routeNode.getStatus(),
                    routeNode.getShapes().stream().map(ShapeDataMapper::dataCoreMapper).collect(Collectors.toList()),
                    routeNode.getStopSequences().stream().map(StopSequenceDataMapper::dataCoreMapper).collect(Collectors.toList()),
                    routeNode.getTrips().stream().map(TripDataMapper::dataCoreMapper).collect(Collectors.toList()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core " + exception.getMessage());
        }
    }
    public static RouteNode dataNodeMapper(Route route){
        try {
            return RouteNode.builder()
                    .id(route.getId())
                    .shortName(route.getShortName())
                    .longName(route.getLongName())
                    .description(route.getDescription())
                    .color(route.getColor())
                    .textColor(route.getTextColor())
                    .progressStatus(route.getProgressStatus())
                    .status(route.getStatus())
                    .shapes(route.getShapes().stream().map(ShapeDataMapper::dataNodeMapper).collect(Collectors.toList()))
                    .stopSequences(route.getStopSequences().stream().map(StopSequenceDataMapper::dataNodeMapper).collect(Collectors.toList()))
                    .trips(route.getTrips().stream().map(TripDataMapper::dataNodeMapper).collect(Collectors.toList()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
    public static RouteGeneralInfoResponseModel mapToGeneralInfo(Map<String, Object> map) {
        return new RouteGeneralInfoResponseModel(
                (String) map.get("shortName"),
                (String) map.get("longName"),
                (String) map.get("description"),
                (String) map.get("color"),
                (String) map.get("textColor"),
                RouteProgressStatus.valueOf((String) map.get("progressStatus")),
                EntityStatus.valueOf((String) map.get("status")));
    }
}