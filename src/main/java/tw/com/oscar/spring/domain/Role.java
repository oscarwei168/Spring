/**
 * Role.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/7
 * <p>
 * H i s t o r y
 * 2015/8/7 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.domain;

import org.hibernate.annotations.Cascade;
import tw.com.oscar.spring.domain.commons.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>
 * Title: Role.java<br>
 * </p>
 * <strong>Description:</strong> A Role entity <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/7
 * @since 2015/8/7
 */
@Entity
@Table(name = "ROLE", uniqueConstraints = @UniqueConstraint(name = "UK_ROLENAME", columnNames = {"ROLE_NAME"}))
public class Role extends BaseEntity {

    private String roleName;

    private Set<Account> accounts;

    public Role() {
    }

    public Role(String roleName) {
        super();
        this.roleName = roleName;
    }

    @Column(name = "ROLE_NAME", nullable = false, length = 45)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "ACCOUNT_ROLE", joinColumns = {@JoinColumn(name = "ID_ROLE")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_ACCOUNT")})
    @org.hibernate.annotations.ForeignKey(name = "FK_ROLE_ACCOUNT")
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
