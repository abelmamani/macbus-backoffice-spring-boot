package auth.usecases;

import auth.inputs.LoginInput;
import auth.models.AuthResponse;
import auth.models.LoginRequestModel;
import auth.models.UserIdentity;
import auth.outputs.LoginRepository;
import user.exceptions.UserNotExistException;
import user.models.User;

public class LoginUseCase implements LoginInput {
    private LoginRepository loginRepository;

    public LoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public AuthResponse login(LoginRequestModel loginRequestModel) {
        if (!loginRepository.authenticate(loginRequestModel.getEmail(), loginRequestModel.getPassword())) {
            throw new UserNotExistException("Correo electrónico y/o contraseña incorrecto.");
        }
        User user = loginRepository.findByEmail(loginRequestModel.getEmail());
        return AuthResponse.getInstance(
                loginRepository.generateToken(user),
                UserIdentity.getInstance(user.getEmail(), user.getRole()));
    }
}
