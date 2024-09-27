package ar.edu.undec.adapter.service.stophistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stophistory.inputs.GetStopHistoryCountsInput;
import stophistory.outputs.StopHistoryRepository;
import stophistory.usecases.GetStopHistoryCountsUseCase;

@Configuration
public class StopHistoryBeanConfig {
    @Bean
    public GetStopHistoryCountsInput getStopHistoryCountsInput(StopHistoryRepository stopHistoryRepository){
        return new GetStopHistoryCountsUseCase(stopHistoryRepository);
    }
}
