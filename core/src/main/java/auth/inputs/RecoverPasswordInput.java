package auth.inputs;

public interface RecoverPasswordInput {
    void sendRecoveryEmail(String email);
    void resetPassword(String token, String newPassword);
}
