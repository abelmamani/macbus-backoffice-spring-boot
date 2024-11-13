package role.models;
import java.util.ArrayList;

public class CreateRoleRequestModel {
    private String name;
    private ArrayList<String> privileges;

    private CreateRoleRequestModel(){}

    public CreateRoleRequestModel(String name, ArrayList<String> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPrivileges() {
        return privileges;
    }
}