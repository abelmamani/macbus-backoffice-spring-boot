package ar.edu.undec.adapter.service.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import role.outputs.RoleRepository;
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
    public CreateUserInput createUserInput(CreateUserRepository createUserRepository, RoleRepository roleRepository){
        return new CreateUserUseCase(createUserRepository, roleRepository);
    }
    @Bean
    public UpdateUserInput updateUserInput(UpdateUserRepository updateUserRepository, RoleRepository roleRepository){
        return new UpdateUserUseCase(updateUserRepository, roleRepository);
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
