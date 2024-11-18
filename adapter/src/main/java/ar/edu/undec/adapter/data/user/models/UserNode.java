package ar.edu.undec.adapter.data.user.models;

import ar.edu.undec.adapter.data.audit.AuditableNode;
import ar.edu.undec.adapter.data.role.models.RoleNode;
import audit.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("User")
public class UserNode extends AuditableNode implements UserDetails {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    @Property(name = "name")
    private String name;
    @Property(name = "last_name")
    private String lastName;
    @Property(name = "email")
    private String email;
    @Property(name = "password")
    private String password;
    @Property(name = "reset_token")
    private String resetToken;
    @Property(name = "token_expiry_date")
    private LocalDateTime tokenExpiryDate;
    @Property(name = "status")
    private EntityStatus status;
    @Relationship(type = "HAS_ROLE")
    private RoleNode role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPrivileges().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}