package tw.com.oscar.spring.util.common.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Oscar on 2015/2/23.
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public class BaseEntity implements Serializable {

    protected Long pid;
    private String userCreated;
    private Date dateCreated;
    private String userLastModified;
    private Date dateLastModified;

    @Id
    @Column(name = "PID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPid() {
        return pid;
    }

    private void setPid(Long pid) {
        this.pid = pid;
    }

    @Column(name = "USER_CREATED", nullable = false, updatable = false)
    @NotNull
    @Length(max = 50)
    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    @Column(name = "DATE_CREATED", updatable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "USER_LAST_MODIFIED", insertable = false)
    @Length(max = 50)
    public String getUserLastModified() {
        return userLastModified;
    }

    public void setUserLastModified(String userLastModified) {
        this.userLastModified = userLastModified;
    }

    @Column(name = "DATE_LAST_MODIFIED", updatable = false)
    @Temporal(TemporalType.DATE)
    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Transient
    public boolean isNew() {
        return (null == this.pid);
    }
}
