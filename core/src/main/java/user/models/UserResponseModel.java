package user.models;

public class UserResponseModel {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String role;
    private String status;

    private UserResponseModel(){}

    public UserResponseModel(String id, String name, String lastName, String email, String role, String status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.status = status;
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

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}
