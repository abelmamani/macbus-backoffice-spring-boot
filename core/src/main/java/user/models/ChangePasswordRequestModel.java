package user.models;

public class ChangePasswordRequestModel {
    private String email;
    private String password;

    private ChangePasswordRequestModel(){}

    public ChangePasswordRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
