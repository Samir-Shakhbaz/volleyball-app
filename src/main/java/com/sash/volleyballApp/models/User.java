package com.sash.volleyballApp.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // e.g., PLAYER, ADMIN

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PlayerProfile playerProfile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role != null
                ? AuthorityUtils.createAuthorityList("ROLE_" + role.toUpperCase())
                : List.of();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
