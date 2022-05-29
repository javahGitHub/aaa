package com.fazliddin.fullyme.entity;

import com.fazliddin.fullyme.entity.templete.AbstractUUID;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update users set deleted = false where id = ?")
public class User extends AbstractUUID implements UserDetails {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

//    @Builder.Default
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Attachment photo;

    private String websiteLink;
    private String githubLink;
    private String linkedInLink;

    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = false;

    public User(String firstName, String lastName, String phoneNumber, Role roleUser) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = phoneNumber;
        role = roleUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissionEnums().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());

    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getFullName(){
        return this.lastName + " " + this.firstName;
    }

}
