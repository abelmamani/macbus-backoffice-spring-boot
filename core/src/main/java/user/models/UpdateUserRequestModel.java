package user.models;

public class UpdateUserRequestModel {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;

    private UpdateUserRequestModel(){
    }
    private UpdateUserRequestModel(Long id, String name, String lastName, String email, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
    public static UpdateUserRequestModel getInstance(Long id, String name, String lastName, String email, String role) {
        return new UpdateUserRequestModel(id, name, lastName, email, role);
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
    public String getRole() {
        return role;
    }
}
