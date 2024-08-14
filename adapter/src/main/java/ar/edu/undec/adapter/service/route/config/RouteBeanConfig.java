package ar.edu.undec.adapter.service.route.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import route.inputs.*;
import route.outputs.*;
import route.usecases.*;

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
