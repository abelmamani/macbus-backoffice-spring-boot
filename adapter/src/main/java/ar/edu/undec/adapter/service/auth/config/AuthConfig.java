package ar.edu.undec.adapter.service.auth.config;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import auth.inputs.LoginInput;
import auth.inputs.RecoverPasswordInput;
import auth.outputs.LoginRepository;
import auth.outputs.RecoverPasswordRepository;
import auth.usecases.LoginUseCase;
import auth.usecases.RecoverPasswordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthConfig {
    private final UserCRUD userCRUD;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailService(){
        return username -> userCRUD.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+username+" no esta registrado."));
    }
    @Bean
    public LoginInput loginInput(LoginRepository loginRepository){
        return new LoginUseCase(loginRepository);
    }
    @Bean
    public RecoverPasswordInput recoverPasswordInput(RecoverPasswordRepository recoverPasswordRepository){
        return new RecoverPasswordUseCase(recoverPasswordRepository);
    }
}
