package br.com.lucasromagnoli.javaee.underpinning.domain.model;

import javax.persistence.*;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Entity
@Table(name = "UND_SYSTEM_ROLES")
public class SystemRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SYSTEM_ROLES")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
