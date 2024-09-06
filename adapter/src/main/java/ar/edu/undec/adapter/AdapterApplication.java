package ar.edu.undec.adapter;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.models.UserNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import user.models.ERole;

import java.time.LocalDateTime;
import java.util.UUID;


@SpringBootApplication
public class AdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserCRUD userCRUD;
    @Bean
    CommandLineRunner init(){
        return args -> {
            if(!userCRUD.existsByEmail("juan@gmail.com")) {
                UserNode userEntity = UserNode.builder()
                        .id(null)
                        .name("JUAN")
                        .lastName("PEREZ")
                        .email("juan@gmail.com")
                        .password(passwordEncoder.encode("1234"))
                        .role(ERole.ROLE_ADMIN)
                        .resetToken(UUID.randomUUID().toString())
                        .tokenExpiryDate(LocalDateTime.now().minusMinutes(1))
                        .build();
                userCRUD.save(userEntity);
            }
        };
    }
}
