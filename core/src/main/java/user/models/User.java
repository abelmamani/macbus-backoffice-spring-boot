package user.models;

import audit.EntityStatus;
import role.models.Role;
import user.exceptions.UserException;
import utils.GmailValidator;
import java.time.LocalDateTime;

public class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String resetToken;
    private LocalDateTime tokenExpiryDate;
    private EntityStatus status;
    private Role role;

    private User(String id, String name, String lastName, String email, String password, String resetToken, LocalDateTime tokenExpiryDate, EntityStatus status, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.resetToken = resetToken;
        this.tokenExpiryDate = tokenExpiryDate;
        this.status = status;
        this.role = role;
    }

    public static User getInstance(String id, String name, String lastName, String email, String password, String resetToken, LocalDateTime tokenExpiryDate, EntityStatus status, Role role) {
        if (name == null || name.isBlank())
            throw new UserException("El nombre del usuario es requerido.");

        if (lastName == null || lastName.isBlank())
            throw new UserException("El apellido del usuario es requerido.");

        if (email == null || email.isBlank())
            throw new UserException("El correo del usuario es requerido.");

        if (!GmailValidator.isValidGmailAddress(email))
            throw new UserException("El correo debe ser una dirección de Gmail válida.");

        if (password == null || password.isBlank())
            throw new UserException("La contraseña del usuario es requerida.");

        //if (!ValidatorUtils.isValidPassword(password))
          //  throw new UserException("La contraseña no es válida. Debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una minúscula y un número.");

        if (status == null)
            throw new UserException("El estado del usuario es requerido.");

        if (role == null)
            throw new UserException("El rol del usuario es requerido.");

        return new User(id, name.trim(), lastName.trim(), email.trim(), password.trim(), resetToken, tokenExpiryDate, status, role);
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getResetToken() {
        return resetToken;
    }
    public LocalDateTime getTokenExpiryDate() {
        return tokenExpiryDate;
    }
    public EntityStatus getStatus() { return status; }
    public Role getRole() {
        return role;
    }
}
