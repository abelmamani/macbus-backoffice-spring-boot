package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.models.User;
import user.outputs.CreateUserRepository;

@AllArgsConstructor
@Service
public class CreateUserRepoImplementation implements CreateUserRepository{
    private UserCRUD createUserCRUD;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean existsByEmail(String email) {
        return createUserCRUD.existsByEmail(email);
    }

    @Override
    public String save(User user) {
        return createUserCRUD.save(UserDataMapper.dataNodeMapper(user)).getId();
    }
}
