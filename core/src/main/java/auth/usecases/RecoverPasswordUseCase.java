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
                user.getRole(),
                token,
                LocalDateTime.now().plusHours(1));


        recoverPasswordRepository.save(updateUser);
        recoverPasswordRepository.sendPasswordResetEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = recoverPasswordRepository.findByResetToken(token)
                .orElseThrow(() -> new InvalidTokenException("El token es invalido."));
        if (user.getTokenExpiryDate().isBefore(LocalDateTime.now()))
            throw new InvalidTokenException("El token ya a expirado.");
        User updateUser = User.getInstance(user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                recoverPasswordRepository.encodePassword(newPassword),
                user.getRole(),
                null,
                null);
        recoverPasswordRepository.save(updateUser);
    }

}
