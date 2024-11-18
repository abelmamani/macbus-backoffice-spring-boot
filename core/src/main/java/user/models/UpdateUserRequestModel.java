package user.models;

public class UpdateUserRequestModel {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String status;
    private String role;

    private UpdateUserRequestModel(){}

    public UpdateUserRequestModel(String id, String name, String lastName, String email, String status, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.role = role;
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

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }
}
