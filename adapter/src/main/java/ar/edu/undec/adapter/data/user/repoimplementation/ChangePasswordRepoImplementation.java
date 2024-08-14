package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.models.User;
import user.outputs.ChangePasswordRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ChangePasswordRepoImplementation implements ChangePasswordRepository {
    private UserCRUD userCRUD;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userCRUD.findByEmail(email).map(UserDataMapper::dataCoreMapper);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void save(User user) {
        userCRUD.save(UserDataMapper.dataNodeMapper(user));
    }
}
