package ar.edu.undec.adapter.data.busservice.mapper;

import ar.edu.undec.adapter.data.calendardate.mapper.CalendarDateDataMapper;
import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.busservice.models.ServiceNode;
import busservice.models.Service;
import busservice.models.ServiceModel;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceDataMapper {
    public static Service dataCoreMapper(ServiceNode serviceNode){
        try {
            return Service.getInstance(serviceNode.getId(),
                    serviceNode.getName(),
                    serviceNode.getStartDate(),
                    serviceNode.getEndDate(),
                    serviceNode.getCalendarDates().stream().map(CalendarDateDataMapper::dataCoreMapper).collect(Collectors.toList()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static ServiceNode dataNodeMapper(Service service){
        try {
            return ServiceNode.builder()
                    .id(service.getId())
                    .name(service.getName())
                    .startDate(service.getStartDate())
                    .endDate(service.getEndDate())
                    .calendarDates(service.getCalendarDates().stream().map(CalendarDateDataMapper::dataNodeMapper).collect(Collectors.toList()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }

    public static ServiceModel mapToServiceModel(Map<String, Object> map) {
        return new ServiceModel(
                (String) map.get("name"),
                (String) map.get("startDate"),
                (String) map.get("endDate"));
    }
}