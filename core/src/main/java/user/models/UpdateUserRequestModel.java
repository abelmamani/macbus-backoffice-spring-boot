package user.models;

public class UpdateUserRequestModel {
    private String name;
    private String lastName;
    private String email;
    private String role;

    private UpdateUserRequestModel(){
    }
    public UpdateUserRequestModel(String name, String lastName, String email, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
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
    public String getRole() {
        return role;
    }
}
