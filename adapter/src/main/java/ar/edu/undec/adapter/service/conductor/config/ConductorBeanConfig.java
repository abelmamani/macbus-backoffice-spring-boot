package ar.edu.undec.adapter.service.conductor.config;

import conductor.inputs.CreateConductorInput;
import conductor.inputs.DeleteConductorInput;
import conductor.inputs.GetConductorsInput;
import conductor.inputs.UpdateConductorInput;
import conductor.outputs.ConductorRepository;
import conductor.usecases.CreateConductorUseCase;
import conductor.usecases.DeleteConductorUseCase;
import conductor.usecases.GetConductorsUseCase;
import conductor.usecases.UpdateConductorsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConductorBeanConfig {
    @Bean
    GetConductorsInput getConductorsInput(ConductorRepository conductorRepository){
        return new GetConductorsUseCase(conductorRepository);
    }
    @Bean
    public CreateConductorInput createConductorInput(ConductorRepository conductorRepository){
        return new CreateConductorUseCase(conductorRepository);
    }
    @Bean
    public UpdateConductorInput updateConductorInput(ConductorRepository conductorRepository){
        return new UpdateConductorsUseCase(conductorRepository);
    }
    @Bean
    public DeleteConductorInput deleteConductorInput(ConductorRepository conductorRepository){
        return new DeleteConductorUseCase(conductorRepository);
    }
}
