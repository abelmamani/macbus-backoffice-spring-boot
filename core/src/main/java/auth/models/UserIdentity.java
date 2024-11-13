package auth.models;

import role.models.Role;

public class UserIdentity {
    private String username;
    private Role role;
    private UserIdentity(){}

    private UserIdentity(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public static UserIdentity getInstance(String username, Role role) {
        return new UserIdentity(username, role);
    }
    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}