package role.models;
import java.util.List;

public class UpdateRoleRequestModel {
    private String id;
    private String name;
    private String status;
    private List<String> privileges;
    private UpdateRoleRequestModel(){}

    public UpdateRoleRequestModel(String id, String name, String status, List<String> privileges) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.privileges = privileges;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getPrivileges() {
        return privileges;
    }
}