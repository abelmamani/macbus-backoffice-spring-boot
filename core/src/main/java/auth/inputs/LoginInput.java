package auth.inputs;

import auth.models.AuthResponse;
import auth.models.LoginRequestModel;

public interface LoginInput {
    AuthResponse login(LoginRequestModel loginRequestModel);
}
