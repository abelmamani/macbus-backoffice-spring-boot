package ar.edu.undec.adapter.service.stopsequene.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import route.inputs.GetRouteInput;
import route.inputs.UpdateRouteInput;
import stop.inputs.CreateStopInput;
import stop.outputs.GetStopRepository;
import stopsequence.inputs.CreateStopSequenceInput;
import stopsequence.inputs.GetStopSequencesByRouteInput;
import stopsequence.usecases.CreateStopSequenceUseCase;
import stopsequence.usecases.GetStopSequencesByRouteUseCase;

@Component
public class StopSequenceBeanConfig {
    @Bean
    CreateStopSequenceInput createStopSequenceInput(GetRouteInput getRouteInput, CreateStopInput createStopInput, UpdateRouteInput updateRouteInput, GetStopRepository getStopRepository){
        return new CreateStopSequenceUseCase(getRouteInput, createStopInput, updateRouteInput, getStopRepository);
    }

    @Bean
    GetStopSequencesByRouteInput getStopSequencesByRouteInput(GetRouteInput getRouteInput){
        return new GetStopSequencesByRouteUseCase(getRouteInput);
    }
}
