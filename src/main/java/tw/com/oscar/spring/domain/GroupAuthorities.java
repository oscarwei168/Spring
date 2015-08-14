/**
 * GroupAuthorities.java
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

import org.hibernate.annotations.ForeignKey;
import tw.com.oscar.spring.domain.commons.BaseEntity;

import javax.persistence.*;

/**
 * <p>
 * Title: GroupAuthorities.java<br>
 * </p>
 * <strong>Description:</strong> A GroupAuthorities entity <br>
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
@Table(name = "GROUP_AUTHORITIES")
public class GroupAuthorities extends BaseEntity {

    private String authority;

    private Groups groups;

    @Column(name = "AUTHORITY", nullable = false, length = 50)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GROUP", nullable = false)
    @ForeignKey(name = "FK_GROUP_AUTHORITIES_GROUP_ID")
    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
