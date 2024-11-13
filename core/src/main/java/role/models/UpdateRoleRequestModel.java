package role.models;
import privilege.models.Privilege;

import java.util.List;

public class UpdateRoleRequestModel {
    private String id;
    private String name;
    private List<String> privileges;
    private UpdateRoleRequestModel(){}

    public UpdateRoleRequestModel(String id, String name, List<String> privileges) {
        this.id = id;
        this.name = name;
        this.privileges = privileges;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPrivileges() {
        return privileges;
    }
}