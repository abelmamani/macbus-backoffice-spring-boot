package ar.edu.undec.adapter.service.stopsequene.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import busroute.outputs.UpdateRouteRepository;
import stop.outputs.GetStopRepository;
import stop.outputs.UpdateStopRepository;
import stopsequence.inputs.CreateStopSequenceInput;
import stopsequence.inputs.DeleteStopSequenceInput;
import stopsequence.inputs.GetStopSequencesByRouteInput;
import stopsequence.outputs.StopSequenceRepository;
import stopsequence.usecases.CreateStopSequenceUseCase;
import stopsequence.usecases.DeleteStopSequenceUseCase;
import stopsequence.usecases.GetStopSequencesByRouteUseCase;

@Component
public class StopSequenceBeanConfig {
    @Bean
    public CreateStopSequenceInput createStopSequenceInput(UpdateRouteRepository updateRouteRepository, GetStopRepository getStopRepository){
        return new CreateStopSequenceUseCase(updateRouteRepository, getStopRepository);
    }
    @Bean
    public DeleteStopSequenceInput deleteStopSequenceInput(UpdateRouteRepository updateRouteRepository, StopSequenceRepository getStopSequenceRepository, UpdateStopRepository updateStopRepository){
        return new DeleteStopSequenceUseCase(updateRouteRepository, getStopSequenceRepository, updateStopRepository);
    }

    @Bean
    public GetStopSequencesByRouteInput getStopSequencesByRouteInput(StopSequenceRepository stopSequenceRepository){
        return new GetStopSequencesByRouteUseCase(stopSequenceRepository);
    }
}
