package ar.edu.undec.adapter.service.report.config;

import busroute.outputs.GetRouteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import report.inputs.GetStatusCountsInput;
import report.usecases.GetSatusCuntsUseCase;
import stop.outputs.GetStopRepository;
import trip.outputs.TripRepository;

@Configuration
public class ReportBeanConfig {
    @Bean
    public GetStatusCountsInput getStatusCountsInput(GetRouteRepository getRouteRepository, GetStopRepository getStopRepository, TripRepository tripRepository){
        return new GetSatusCuntsUseCase(getRouteRepository, getStopRepository, tripRepository);
    }
}
