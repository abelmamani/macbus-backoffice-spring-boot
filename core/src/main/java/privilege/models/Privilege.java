package privilege.models;

import privilege.exceptions.PrivilegeException;

public class Privilege {
    private String id;
    private String name;

    private Privilege(){}

    private Privilege(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Privilege getInstance(String id, String name){
        if(name == null || name.isBlank())
            throw new PrivilegeException("El nombre del privilegio es requerido.");
        return new Privilege(id, name.trim());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

