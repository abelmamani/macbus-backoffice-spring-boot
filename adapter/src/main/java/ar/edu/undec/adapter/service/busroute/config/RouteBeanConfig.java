package ar.edu.undec.adapter.service.busroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import busroute.inputs.*;
import busroute.outputs.*;
import busroute.usecases.*;

@Configuration
public class RouteBeanConfig {
    @Bean
    public GetRoutesInput getRoutesInput(GetRoutesRepository getRoutesRepository){
        return new GetRoutesUseCase(getRoutesRepository);
    }
    @Bean
    public GetRouteInput getRouteInput(GetRouteRepository getRouteRepository){
        return new GetRouteUseCase(getRouteRepository);
    }
    @Bean
    public CreateRouteInput createRouteInput(CreateRouteRepository createRouteRepository){
        return new CreateRouteUseCase(createRouteRepository);
    }
    @Bean
    public UpdateGeneralInfoInput updateGeneralInfoInput(UpdateGeneralInfoRepository updateGeneralInfoRepository){
        return new UpdateGeneralInfoUseCase(updateGeneralInfoRepository);
    }
    @Bean
    public DeleteRouteInput deleteRouteInput(DeleteRouteRepository deleteRouteRepository){
        return new DeleteRouteUseCase(deleteRouteRepository);
    }
}
