package ar.edu.undec.adapter.service.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import route.outputs.UpdateRouteRepository;
import service.outputs.GetServiceRepository;
import trip.inputs.CreateTripInput;
import trip.inputs.DeleteTripInput;
import trip.inputs.GetTripsByRouteInput;
import trip.outputs.TripRepository;
import trip.usecases.CreateTripUseCase;
import trip.usecases.DeleteTripUseCase;
import trip.usecases.GetTripsByRouteUseCase;

@Component
public class TripBeanConfig {
    @Bean
    public GetTripsByRouteInput getTripsByRouteInput(TripRepository getTripsByRouteRepository){
        return new GetTripsByRouteUseCase(getTripsByRouteRepository);
    }
    @Bean
    public CreateTripInput createTripInput(UpdateRouteRepository updateRouteRepository, GetServiceRepository getServiceRepository){
        return new CreateTripUseCase(updateRouteRepository, getServiceRepository);
    }
    @Bean
    public DeleteTripInput deleteTripInput(UpdateRouteRepository updateRouteRepository){
        return new DeleteTripUseCase(updateRouteRepository);
    }
}
