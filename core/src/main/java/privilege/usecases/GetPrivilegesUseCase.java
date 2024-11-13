package privilege.usecases;

import privilege.inputs.GetPrivilegesInput;
import privilege.models.Privilege;
import privilege.outputs.PrivilegeRepository;
import java.util.Collection;

public class GetPrivilegesUseCase implements GetPrivilegesInput {
    private PrivilegeRepository privilegeRepository;

    public GetPrivilegesUseCase(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Collection<Privilege> getPrivileges() {
        return privilegeRepository.findAll();
    }
}
