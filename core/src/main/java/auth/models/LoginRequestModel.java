package auth.models;

public class LoginRequestModel {
    private String email;
    private String password;
    private LoginRequestModel(){}
    private LoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public static LoginRequestModel getInstance(String email, String password) {
        return new LoginRequestModel(email, password);
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}