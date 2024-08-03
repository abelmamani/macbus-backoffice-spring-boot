package auth.models;
public class AuthResponse {
    private String token;
    private UserIdentity identity;
    private AuthResponse(){}
    private AuthResponse(String token, UserIdentity identity) {
        this.token = token;
        this.identity = identity;
    }
    public static AuthResponse getInstance(String token, UserIdentity identity){
        return new AuthResponse(token, identity);
    }
    public String getToken() {
        return token;
    }

    public UserIdentity getIdentity() {
        return identity;
    }
}
