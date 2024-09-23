package ar.edu.undec.adapter.data.conductor.repoimplementations;

import ar.edu.undec.adapter.data.conductor.mapper.ConductorDataMapper;
import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import conductor.models.ConductorResponseModel;
import conductor.outputs.ConductorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.models.User;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ConductorRepoImplementation implements ConductorRepository {
    private UserCRUD userCRUD;
    private final PasswordEncoder passwordEncoder;
    @Override
    public boolean existsByEmail(String email) {
        return userCRUD.existsByEmail(email);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userCRUD.findByEmail(email).map(UserDataMapper::dataCoreMapper);
    }

    @Override
    public void deleteByEmail(String email) {
        userCRUD.deleteByEmail(email);
    }

    @Override
    public void save(User user) {
        userCRUD.save(UserDataMapper.dataNodeMapper(user));
    }

    @Override
    public Collection<ConductorResponseModel> findConductors() {
        return userCRUD.findConductors()
                .stream()
                .map(ConductorDataMapper::mapToConductorResponseModel)
                .collect(Collectors.toList());
    }
}
