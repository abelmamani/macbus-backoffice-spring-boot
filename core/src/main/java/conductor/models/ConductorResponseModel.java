package conductor.models;

public class ConductorResponseModel {
    private String id;
    private String name;
    private String lastName;
    private String email;

    public ConductorResponseModel(){
    }
    public ConductorResponseModel(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
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
}
