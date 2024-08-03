package ar.edu.undec.adapter.service.stop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stop.inputs.*;
import stop.outputs.*;
import stop.usecases.*;

@Configuration
public class StopBeanConfig {
    @Bean
    public GetStopsInput getStopsInput(GetStopsRepository getStopsRepository){
        return new GetStopsUseCase(getStopsRepository);
    }
    @Bean
    public GetStopInput getStopInput(GetStopRepository getStopRepository){
        return new GetStopUseCase(getStopRepository);
    }
    @Bean
    public CreateStopInput createStopInput(CreateStopRepository createStopRepository){
        return new CreateStopUseCase(createStopRepository);
    }
    @Bean
    public UpdateStopInput updateStopInput(UpdateStopRepository updateStopRepository, GetStopInput getStopInput){
        return new UpdateStopUseCase(updateStopRepository, getStopInput);
    }
    @Bean
    public DeleteStopInput deleteStopInput(DeleteStopRepository deleteStopRepository){
        return new DeleteStopUseCase(deleteStopRepository);
    }
}
