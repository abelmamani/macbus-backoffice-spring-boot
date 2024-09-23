package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import user.outputs.DeleteUserRepository;

@AllArgsConstructor
@Service
public class DeleteUserRepoImplementation implements DeleteUserRepository {
    private UserCRUD userCRUD;

    @Override
    public boolean existsByEmail(String email) {
        return userCRUD.existsByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        userCRUD.deleteByEmail(email);
    }
}
