package br.com.lucasromagnoli.javaee.underpinning.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
@Entity
@Table(name = "UND_SYSTEM_USER")
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SYSTEM_USER")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UND_SYSTEM_USER_ROLES",
            joinColumns = @JoinColumn(name = "ID_SYSTEM_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_SYSTEM_ROLES")
    )
    private List<SystemRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SystemRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SystemRole> roles) {
        this.roles = roles;
    }
}
