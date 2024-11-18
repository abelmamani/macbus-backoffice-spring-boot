package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.models.User;
import user.outputs.UpdateUserRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateUserRepoImplementation implements UpdateUserRepository {
    private UserCRUD userCRUD;
    private final PasswordEncoder passwordEncoder;
    @Override
    public boolean existsByEmail(String email) {
        return userCRUD.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(String id) {
        return userCRUD.findById(id).map(UserDataMapper::dataCoreMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userCRUD.findByEmailAndStatus(email, EntityStatus.ACTIVE).map(UserDataMapper::dataCoreMapper);
    }

    @Override
    public void update(User user) {
        userCRUD.save(UserDataMapper.dataNodeMapper(user));
    }
}
