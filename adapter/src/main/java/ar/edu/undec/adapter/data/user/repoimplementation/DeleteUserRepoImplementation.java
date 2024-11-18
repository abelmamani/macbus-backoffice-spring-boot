package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import user.models.User;
import user.outputs.DeleteUserRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DeleteUserRepoImplementation implements DeleteUserRepository {
    private UserCRUD userCRUD;

    @Override
    public Optional<User> findByEmail(String email) {
        return userCRUD.findByEmailAndStatus(email, EntityStatus.ACTIVE).map(UserDataMapper::dataCoreMapper);
    }

    @Override
    public void update(User user) {
        userCRUD.save(UserDataMapper.dataNodeMapper(user));
    }
}
