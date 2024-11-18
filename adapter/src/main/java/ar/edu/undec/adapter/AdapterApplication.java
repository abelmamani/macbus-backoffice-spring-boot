package ar.edu.undec.adapter;

import ar.edu.undec.adapter.data.privilege.crud.PrivilegeCRUD;
import ar.edu.undec.adapter.data.privilege.models.PrivilegeNode;
import ar.edu.undec.adapter.data.role.crud.RoleCRUD;
import ar.edu.undec.adapter.data.role.models.RoleNode;
import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.models.UserNode;
import audit.EntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import privilege.models.EPrivilege;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@SpringBootApplication
public class AdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserCRUD userCRUD;
    @Autowired
    RoleCRUD roleCRUD;
    @Autowired
    PrivilegeCRUD privilegeCRUD;
    @Bean
    CommandLineRunner init() {
        return args -> {
            List<PrivilegeNode> privilegeNodes = Arrays.stream(EPrivilege.values())
                    .map(privilege -> {
                        return privilegeCRUD.findByName(privilege.name()).orElseGet(() -> {
                            PrivilegeNode privilegeNode = PrivilegeNode.builder()
                                    .name(privilege.name())
                                    .build();
                            return privilegeCRUD.save(privilegeNode);
                        });
                    })
                    .collect(Collectors.toList());

            RoleNode adminRole = roleCRUD.findByName("ADMIN").orElseGet(() -> {
                RoleNode role = RoleNode.builder()
                        .name("ADMIN")
                        .status(EntityStatus.ACTIVE)
                        .privileges(privilegeNodes)
                        .build();
                return roleCRUD.save(role);
            });
            if (!userCRUD.existsByEmail("abelmamani186f@gmail.com")) {
                UserNode superAdminUser = UserNode.builder()
                        .name("ABEL")
                        .lastName("MAMANI")
                        .email("abelmamani186f@gmail.com")
                        .password(passwordEncoder.encode("aA12345678"))
                        .resetToken(UUID.randomUUID().toString())
                        .tokenExpiryDate(LocalDateTime.now().minusMinutes(1))
                        .status(EntityStatus.ACTIVE)
                        .role(adminRole)
                        .build();
                userCRUD.save(superAdminUser);
            }
        };
    }
}
