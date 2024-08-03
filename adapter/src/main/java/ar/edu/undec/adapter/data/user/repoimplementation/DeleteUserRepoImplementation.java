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
    public boolean existsById(Long id) {
        return userCRUD.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        userCRUD.deleteById(id);
    }
}
