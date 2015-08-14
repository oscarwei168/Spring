/**
 * GroupMembers.java
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

/**
 * <p>
 * Title: GroupMembers.java<br>
 * </p>
 * <strong>Description:</strong> A GroupMembers entity<br>
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
@Table(name = "GROUP_MEMBERS")
public class GroupMembers extends BaseEntity {

    private String username;

    private Groups group;

    @Column(name = "USERNAME", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GROUP", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "FK_GROUP_MEMBERS_GROUP_ID")
    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }
}
