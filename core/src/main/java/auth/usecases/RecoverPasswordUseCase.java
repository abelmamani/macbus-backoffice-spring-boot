package auth.usecases;

import auth.exceptions.InvalidTokenException;
import auth.inputs.RecoverPasswordInput;
import auth.outputs.RecoverPasswordRepository;
import user.exceptions.UserNotExistException;
import user.models.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RecoverPasswordUseCase implements RecoverPasswordInput {
    private RecoverPasswordRepository recoverPasswordRepository;

    public RecoverPasswordUseCase(RecoverPasswordRepository recoverPasswordRepository) {
        this.recoverPasswordRepository = recoverPasswordRepository;
    }

    @Override
    public void sendRecoveryEmail(String email) {
        User user = recoverPasswordRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException("El usuario con email "+email+" no existe"));
        String token = UUID.randomUUID().toString();
        User updateUser = User.getInstance(user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                token,
                LocalDateTime.now().plusHours(1),
                user.getStatus(),
                user.getRole());

        recoverPasswordRepository.save(updateUser);
        recoverPasswordRepository.sendPasswordResetEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = recoverPasswordRepository.findByResetToken(token)
                .orElseThrow(() -> new InvalidTokenException("Enlace de recuperación inválido"));
        if (user.getTokenExpiryDate().isBefore(LocalDateTime.now()))
            throw new InvalidTokenException("El enlace de recuperación ya ha expirado");
        User updateUser = User.getInstance(user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                recoverPasswordRepository.encodePassword(newPassword),
                "",
                LocalDateTime.now().minusMinutes(1),
                user.getStatus(),
                user.getRole());
        recoverPasswordRepository.save(updateUser);
    }
}
