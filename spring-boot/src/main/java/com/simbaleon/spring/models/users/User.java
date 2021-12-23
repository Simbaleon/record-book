package com.simbaleon.spring.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")},
        name = "users", schema = "public")
public class User extends RepresentationModel<User> implements UserDetails, Identifiable<Long> {

    public final static String ROLE_EMPLOYEE = "ROLE_EMPLOYEE",
            ROLE_ADMIN = "ROLE_ADMIN",
            ROLE_STUDENT = "ROLE_STUDENT",
            ROLE_PROFESSOR = "ROLE_PROFESSOR";

    public enum Role implements GrantedAuthority {
        ROLE_EMPLOYEE, ROLE_ADMIN, ROLE_STUDENT, ROLE_PROFESSOR;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Email
    @Column
    private String username;
    @NotEmpty
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    @Column
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
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

