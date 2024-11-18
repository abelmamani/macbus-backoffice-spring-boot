package ar.edu.undec.adapter.data.role.repoimplementation;

import ar.edu.undec.adapter.data.role.crud.RoleCRUD;
import ar.edu.undec.adapter.data.role.mapper.RoleDataMapper;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import role.models.Role;
import role.outputs.RoleRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleRepoImplementation implements RoleRepository {
    private RoleCRUD roleCRUD;
    @Override
    public boolean existsById(String id) {
        return roleCRUD.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return roleCRUD.existsByName(name);
    }

    @Override
    public boolean isUsedByUsers(String id) {
        return roleCRUD.isUsedByUsers(id);
    }

    @Override
    public Optional<Role> findById(String id) {
        return roleCRUD.findById(id).map(RoleDataMapper::dataCoreMapper);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleCRUD.findByNameAndStatus(name, EntityStatus.ACTIVE).map(RoleDataMapper::dataCoreMapper);
    }

    @Override
    public void deleteById(String id) {
        roleCRUD.deleteById(id);
    }

    @Override
    public void save(Role role) {
        roleCRUD.save(RoleDataMapper.dataNodeMapper(role));
    }

    @Override
    public Collection<Role> findAll() {
        return roleCRUD.findAll().stream().map(RoleDataMapper::dataCoreMapper).collect(Collectors.toList());
    }

    @Override
    public Collection<Role> findAllByStatus(EntityStatus status) {
        return roleCRUD.findAllByStatus(status).stream().map(RoleDataMapper::dataCoreMapper).collect(Collectors.toList());
    }
}
