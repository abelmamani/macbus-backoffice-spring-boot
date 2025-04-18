package user.models;

public class CreateUserRequestModel {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;

    private CreateUserRequestModel(){
    }

    public CreateUserRequestModel(String name, String lastName, String email, String password, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
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
    public String getRole() {
        return role;
    }
}
