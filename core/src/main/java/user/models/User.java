package user.models;


import user.exceptions.UserException;
import utils.GmailValidator;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private ERole role;

    private User(Long id, String name, String lastName, String email, String password, ERole role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User getInstance(Long id, String name, String lastName, String email, String password, ERole role) {
        if (name == null || name.isBlank())
            throw new UserException("El nombre del usuario es requerido.");

        if (lastName == null || lastName.isBlank())
            throw new UserException("El apellido del usuario es requerido.");

        if (email == null || email.isBlank())
            throw new UserException("El correo del usuario es requerido.");

        //if (!GmailValidator.isValidGmailAddress(email))
            //throw new UserException("El correo debe ser una dirección de Gmail válida.");

        if (password == null || password.isBlank())
            throw new UserException("La contraseña del usuario es requerida.");

        if (role == null)
            throw new UserException("El rol del usuario es requerido.");

        return new User(id, name, lastName, email, password, role);
    }

    public Long getId() {
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
    public ERole getRole() {
        return role;
    }
}
