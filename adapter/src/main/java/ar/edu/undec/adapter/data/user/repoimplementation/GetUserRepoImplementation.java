package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import user.models.User;
import user.outputs.GetUserRepository;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GetUserRepoImplementation implements GetUserRepository {
    private UserCRUD userCRUD;
    @Override
    public Optional<User> findById(Long id) {
        return userCRUD.findById(id).map(UserDataMapper::dataCoreMapper);
    }
}
