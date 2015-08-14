/**
 * Groups.java
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

import tw.com.oscar.spring.domain.commons.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>
 * Title: Groups.java<br>
 * </p>
 * <strong>Description:</strong> A Groups entity<br>
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
@Table(name = "GROUPS", uniqueConstraints = @UniqueConstraint(name = "UK_GROUPNAME", columnNames = {"GROUP_NAME"}))
public class Groups extends BaseEntity {

    private String groupName;

    private Set<GroupAuthorities> groupAuthorities;

    @Column(name = "GROUP_NAME", nullable = false, length = 50)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @OneToMany(mappedBy = "groups")
    @org.hibernate.annotations.ForeignKey(name = "FK_GROUPS_AUTHORITIES_GROUP")
    public Set<GroupAuthorities> getGroupAuthorities() {
        return groupAuthorities;
    }

    public void setGroupAuthorities(Set<GroupAuthorities> groupAuthorities) {
        this.groupAuthorities = groupAuthorities;
    }
}
