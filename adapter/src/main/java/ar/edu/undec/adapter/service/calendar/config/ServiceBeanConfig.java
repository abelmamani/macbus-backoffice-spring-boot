package ar.edu.undec.adapter.service.calendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.inputs.*;
import service.outputs.*;
import service.usecases.*;

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
    public UpdateServiceInput updateServiceInput(UpdateServiceRepository updateServiceRepository, GetServiceInput getServiceInput){
        return new UpdateServiceUseCase(updateServiceRepository, getServiceInput);
    }
    @Bean
    public DeleteServiceInput deleteServiceInput(DeleteServiceRepository deleteServiceRepository){
        return new DeleteServiceUseCase(deleteServiceRepository);
    }
}
