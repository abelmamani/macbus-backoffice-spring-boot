package ar.edu.undec.adapter.data.auth.repoimplementations;

import ar.edu.undec.adapter.data.auth.exceptions.UsernameNotFoundException;
import ar.edu.undec.adapter.data.auth.services.JwtService;
import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import audit.EntityStatus;
import auth.outputs.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.models.User;

@Service
@RequiredArgsConstructor
public class LoginRepoImplementation implements LoginRepository {
    private final UserCRUD userCRUD;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public boolean authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public User findByEmail(String email) {
        return UserDataMapper.dataCoreMapper(userCRUD.findByEmailAndStatus(email, EntityStatus.ACTIVE)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+email+" no existe.")));
    }

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(user);
    }
}
