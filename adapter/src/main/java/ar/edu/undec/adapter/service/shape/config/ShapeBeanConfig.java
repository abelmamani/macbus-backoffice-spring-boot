package ar.edu.undec.adapter.service.shape.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import route.inputs.GetRouteInput;
import route.inputs.UpdateRouteInput;
import shape.inpúts.CreateShapeInput;
import shape.inpúts.GetShapesByRouteInput;
import shape.outputs.GetShapesByRouteRepository;
import shape.usecases.CreateShapeUseCase;
import shape.usecases.GetShapesByRouteUseCase;

@Component
public class ShapeBeanConfig {
    @Bean
    CreateShapeInput createShapeInput(GetRouteInput getRouteInput, UpdateRouteInput updateRouteInput){
        return new CreateShapeUseCase(getRouteInput, updateRouteInput);
    }

    @Bean
    GetShapesByRouteInput getShapesByRouteInput(GetShapesByRouteRepository getShapesByRouteRepository){
        return new GetShapesByRouteUseCase(getShapesByRouteRepository);
    }
}
