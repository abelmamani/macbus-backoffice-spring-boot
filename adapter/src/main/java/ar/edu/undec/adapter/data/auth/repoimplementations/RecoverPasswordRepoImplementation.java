package ar.edu.undec.adapter.data.auth.repoimplementations;

import ar.edu.undec.adapter.data.user.crud.UserCRUD;
import ar.edu.undec.adapter.data.user.mapper.UserDataMapper;
import audit.EntityStatus;
import auth.outputs.RecoverPasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.models.User;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RecoverPasswordRepoImplementation implements RecoverPasswordRepository {
    private final UserCRUD userCRUD;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    @Override
    public Optional<User> findByEmail(String email) {
        return userCRUD.findByEmailAndStatus(email, EntityStatus.ACTIVE).map(UserDataMapper::dataCoreMapper);
    }

    @Override
    public void sendPasswordResetEmail(String email, String resetToken) {
        String subject = "Recuperación de contraseña";
        String text = "Para restablecer tu contraseña, haz clic en el siguiente enlace: "
                + "http://localhost:4200/reset-password/" + resetToken;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    @Override
    public Optional<User> findByResetToken(String token) {
        return userCRUD.findByResetToken(token).map(UserDataMapper::dataCoreMapper);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void save(User user) {
        userCRUD.save(UserDataMapper.dataNodeMapper(user));
    }
}
