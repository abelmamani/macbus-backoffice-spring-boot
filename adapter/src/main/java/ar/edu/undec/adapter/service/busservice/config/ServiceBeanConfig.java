package ar.edu.undec.adapter.service.busservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import busservice.inputs.*;
import busservice.outputs.*;
import busservice.usecases.*;
import trip.outputs.TripRepository;

@Configuration
public class ServiceBeanConfig {
    @Bean
    public GetServicesInput getServicesInput(GetServicesRepository getServicesRepository){
        return new GetServicesUseCase(getServicesRepository);
    }
    @Bean
    public GetServiceInput getServiceInput(GetServiceRepository getServiceRepository){
        return new GetServiceUseCase(getServiceRepository);
    }
    @Bean
    public CreateServiceInput createServiceInput(CreateServiceRepository createServiceRepository){
        return new CreateServiceUseCase(createServiceRepository);
    }
    @Bean
    public UpdateServiceInput updateServiceInput(UpdateServiceRepository updateServiceRepository){
        return new UpdateServiceUseCase(updateServiceRepository);
    }
    @Bean
    public DeleteServiceInput deleteServiceInput(DeleteServiceRepository deleteServiceRepository, TripRepository tripRepository){
        return new DeleteServiceUseCase(deleteServiceRepository, tripRepository);
    }
}
