package ar.edu.undec.adapter.data.service.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.service.models.ServiceNode;
import service.models.Service;

public class ServiceDataMapper {
    public static Service dataCoreMapper(ServiceNode serviceNode){
        try {
            return Service.getInstance(serviceNode.getId(),
                    serviceNode.getName(),
                    serviceNode.getStartDate(),
                    serviceNode.getEndDate());
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
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}