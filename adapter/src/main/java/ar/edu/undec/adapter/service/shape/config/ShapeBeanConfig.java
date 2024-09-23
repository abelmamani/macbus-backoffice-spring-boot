package ar.edu.undec.adapter.service.shape.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import busroute.outputs.UpdateRouteRepository;
import shape.inpúts.CreateShapeInput;
import shape.inpúts.GetShapesByRouteInput;
import shape.outputs.ShapeRepository;
import shape.usecases.CreateShapeUseCase;
import shape.usecases.GetShapesByRouteUseCase;

@Component
public class ShapeBeanConfig {
    @Bean
    public CreateShapeInput createShapeInput(UpdateRouteRepository updateRouteRepository, ShapeRepository shapeRepository){
        return new CreateShapeUseCase(updateRouteRepository, shapeRepository);
    }

    @Bean
    public GetShapesByRouteInput getShapesByRouteInput(ShapeRepository shapeRepository){
        return new GetShapesByRouteUseCase(shapeRepository);
    }
}
