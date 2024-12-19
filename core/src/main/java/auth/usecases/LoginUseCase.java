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
        User user = loginRepository.findByEmail(loginRequestModel.getEmail());
        if (!loginRepository.authenticate(loginRequestModel.getEmail(), loginRequestModel.getPassword())) {
            throw new UserNotExistException("Contraseña iconrrecto.");
        }
        return AuthResponse.getInstance(
                loginRepository.generateToken(user),
                UserIdentity.getInstance(user.getEmail(), user.getRole()));
    }
}
