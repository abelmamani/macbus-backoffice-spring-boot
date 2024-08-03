package auth.models;
public class UserIdentity {
    private String username;
    private String role;
    private UserIdentity(){}
    private UserIdentity(String username, String role) {
        this.username = username;
        this.role = role;
    }
    public static UserIdentity getInstance(String username, String role) {
        return new UserIdentity(username, role);
    }
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}