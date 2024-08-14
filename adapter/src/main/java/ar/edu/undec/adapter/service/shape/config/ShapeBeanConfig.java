package ar.edu.undec.adapter.service.shape.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import route.inputs.GetRouteInput;
import route.inputs.UpdateRouteInput;
import route.outputs.UpdateRouteRepository;
import shape.inpúts.CreateShapeInput;
import shape.inpúts.GetShapesByRouteInput;
import shape.usecases.CreateShapeUseCase;
import shape.usecases.GetShapesByRouteUseCase;

@Component
public class ShapeBeanConfig {
    @Bean
    public CreateShapeInput createShapeInput(UpdateRouteRepository updateRouteRepository){
        return new CreateShapeUseCase(updateRouteRepository);
    }

    @Bean
    public GetShapesByRouteInput getShapesByRouteInput(GetRouteInput getRouteInput){
        return new GetShapesByRouteUseCase(getRouteInput);
    }
}
