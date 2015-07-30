/**
 * BaseEntity.java
 * Title: Oscar Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/25
 * <p>
 * H i s t o r y
 * 2015/7/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.domain.commons;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: BaseEntity.java
 * </p>
 * <strong>Description:</strong> A base entity class for all other domain object extended with<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/25
 * @since 2015/7/25
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
@DynamicInsert
@DynamicUpdate
public class BaseEntity extends ID implements Serializable {

    private String userCreated;
    private Date dateCreated;
    private String userLastModified;
    private Date dateLastModified;

    /**
     * A getter for 'userCreated' property
     *
     * @return a userCreated value
     */
    @Column(name = "USER_CREATED", nullable = false, updatable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    /**
     * A getter for 'dateCreated' property
     *
     * @return a dateCreated value
     */
    @Column(name = "DATE_CREATED", nullable = false, updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * A getter for 'userLastModified' property
     *
     * @return a userLastModified value
     */
    @Column(name = "USER_LAST_MODIFIED", insertable = false, length = 50)
    @Size(max = 50)
    public String getUserLastModified() {
        return userLastModified;
    }

    public void setUserLastModified(String userLastModified) {
        this.userLastModified = userLastModified;
    }

    /**
     * A getter for 'dateLastModified' property
     *
     * @return a dateLastModified value
     */
    @Column(name = "DATE_LAST_MODIFIED", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Transient
    public boolean isNew() {
        return (null == this.id);
    }
}
