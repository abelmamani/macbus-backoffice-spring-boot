package ar.edu.undec.adapter.service.calendardate.config;

import busservice.outputs.UpdateServiceRepository;
import calendardate.inputs.CreateCalendarDateInput;
import calendardate.inputs.DeleteCalendarDateInput;
import calendardate.inputs.GetCalendarDatesByServiceInput;
import calendardate.outputs.CalendarDateRepository;
import calendardate.usecases.CreateCalendarDateUseCase;
import calendardate.usecases.DeleteCalendarDateUseCase;
import calendardate.usecases.GetCalendarDatesByServiceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalendarDateBeanConfig {
    @Bean
    public GetCalendarDatesByServiceInput getCalendarDatesByServiceInput(CalendarDateRepository calendarDateRepository){
        return new GetCalendarDatesByServiceUseCase(calendarDateRepository);
    }
    @Bean
    public CreateCalendarDateInput createCalendarDateInput(CalendarDateRepository calendarDateRepository, UpdateServiceRepository updateServiceRepository){
        return new CreateCalendarDateUseCase(calendarDateRepository, updateServiceRepository);
    }
    @Bean
    public DeleteCalendarDateInput deleteCalendarDateInput(CalendarDateRepository calendarDateRepository){
        return new DeleteCalendarDateUseCase(calendarDateRepository);
    }
}
