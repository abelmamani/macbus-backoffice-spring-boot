package ar.edu.undec.adapter.data.user.repoimplementation;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import user.models.UserResponseModel;
import user.outputs.GetUsersRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetUsersRepoImplementation implements GetUsersRepository {
    private UserCRUD userCRUD;
    @Override
    public Collection<UserResponseModel> findAll() {
        return userCRUD.findAllUsers().stream().map(UserDataMapper::mapToUserResponseModel).collect(Collectors.toList());
    }
}
