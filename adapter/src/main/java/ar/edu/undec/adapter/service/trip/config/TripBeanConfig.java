package ar.edu.undec.adapter.service.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import busroute.outputs.UpdateRouteRepository;
import busservice.outputs.GetServiceRepository;
import stopsequence.outputs.StopSequenceRepository;
import trip.inputs.CreateTripInput;
import trip.inputs.DeleteTripInput;
import trip.inputs.GetTripsByRouteInput;
import trip.outputs.TripRepository;
import trip.usecases.CreateTripUseCase;
import trip.usecases.DeleteTripUseCase;
import trip.usecases.GetTripsByRouteUseCase;
import tripupdate.inputs.GetTripUpdatesInput;
import tripupdate.inputs.StopTripUpdateInput;
import tripupdate.outputs.TripUpdateRepository;
import tripupdate.usecases.GetTripUpdatesUseCase;
import tripupdate.usecases.StopTripUpdateUseCase;

@Component
public class TripBeanConfig {
    @Bean
    public GetTripsByRouteInput getTripsByRouteInput(TripRepository getTripsByRouteRepository){
        return new GetTripsByRouteUseCase(getTripsByRouteRepository);
    }
    @Bean
    public CreateTripInput createTripInput(TripRepository tripRepository, UpdateRouteRepository updateRouteRepository, GetServiceRepository getServiceRepository, StopSequenceRepository stopSequenceRepository){
        return new CreateTripUseCase(tripRepository, updateRouteRepository, getServiceRepository, stopSequenceRepository);
    }
    @Bean
    public DeleteTripInput deleteTripInput(UpdateRouteRepository updateRouteRepository, TripRepository tripRepository){
        return new DeleteTripUseCase(updateRouteRepository, tripRepository);
    }
    @Bean
    public GetTripUpdatesInput getTripUpdatesInput(TripUpdateRepository tripUpdateRepository){
        return new GetTripUpdatesUseCase(tripUpdateRepository);
    }
    @Bean
    public StopTripUpdateInput stopTripUpdateInput(TripUpdateRepository tripUpdateRepository){
        return new StopTripUpdateUseCase(tripUpdateRepository);
    }
}
