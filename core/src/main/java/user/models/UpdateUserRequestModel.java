package user.models;

public class UpdateUserRequestModel {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;

    private UpdateUserRequestModel(){
    }
    private UpdateUserRequestModel(String name, String lastName, String email, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
    public static UpdateUserRequestModel getInstance(String name, String lastName, String email, String role) {
        return new UpdateUserRequestModel(name, lastName, email, role);
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
    public String getRole() {
        return role;
    }
}
