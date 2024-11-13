package ar.edu.undec.adapter.data.privilege.repoimplementation;

import ar.edu.undec.adapter.data.privilege.crud.PrivilegeCRUD;
import ar.edu.undec.adapter.data.privilege.mapper.PrivilegeDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import privilege.models.Privilege;
import privilege.outputs.PrivilegeRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PrivilegeRepoImplementation implements PrivilegeRepository {
    private PrivilegeCRUD privilegeCRUD;
    @Override
    public boolean existsByName(String name) {
        return privilegeCRUD.existsByName(name);
    }

    @Override
    public Optional<Privilege> findByName(String name) {
        return privilegeCRUD.findByName(name).map(PrivilegeDataMapper::dataCoreMapper);
    }

    @Override
    public Collection<Privilege> findAll() {
        return privilegeCRUD.findAll().stream().map(PrivilegeDataMapper::dataCoreMapper).collect(Collectors.toList());
    }
}
