/**
 * Permission.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/9/19
 * <p>
 * H i s t o r y
 * 2015/9/19 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.domain;

import org.hibernate.annotations.Cascade;
import tw.com.oscar.spring.domain.commons.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>
 * Title: Permission.java<br>
 * </p>
 * <strong>Description:</strong> A permission entity object <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/9/19
 * @since 2015/9/19
 */
@Entity
@Table(name = "PERMISSION", uniqueConstraints = @UniqueConstraint(name = "UK_PERMISSION_NAME", columnNames =
        {"NAME"}))
public class Permission extends BaseEntity {

    private String name;
    private String description;

    Set<Role> roles;

    public Permission() {
    }

    public Permission(String name) {
        super();
        this.name = name;
    }

    @Column(name = "NAME", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = {@JoinColumn(name = "ID_PERMISSION")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_ROLE")})
    @org.hibernate.annotations.ForeignKey(name = "FK_PERMISSION_ROLE")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return !(getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
