package ar.edu.undec.adapter.service.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.inputs.*;
import user.outputs.*;
import user.usecases.*;

@Configuration
public class UserBeanConfig {
    @Bean
    public GetUsersInput getUsersInput(GetUsersRepository getUsersRepository){
        return new GetUsersUseCase(getUsersRepository);
    }
    @Bean
    public GetUserInput getUserInput(GetUserRepository getUserRepository){
        return new GetUserUseCase(getUserRepository);
    }
    @Bean
    public CreateUserInput createUserInput(CreateUserRepository createUserRepository){
        return new CreateUserUseCase(createUserRepository);
    }
    @Bean
    public UpdateUserInput updateUserInput(UpdateUserRepository updateUserRepository){
        return new UpdateUserUseCase(updateUserRepository);
    }
    @Bean
    public ChangePasswordInput changePasswordInput(ChangePasswordRepository changePasswordRepository){
        return new ChangePasswordUseCase(changePasswordRepository);
    }
    @Bean
    public DeleteUserInput deleteUserInput(DeleteUserRepository deleteUserRepository){
        return new DeleteUserUseCase(deleteUserRepository);
    }
}
