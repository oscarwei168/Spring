/**
 * PersistentLogins.java
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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: PersistentLogins.java<br>
 * </p>
 * <strong>Description:</strong> A spring security remember me token persistent object<br>
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
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogins implements Serializable {

    private String username;
    private String series;
    private String token;
    private Date lastUsed;

    public PersistentLogins() {
    }

    @Column(name = "USERNAME", nullable = false, length = 64, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "SERIES", nullable = false, length = 64, updatable = false)
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Column(name = "TOKEN", nullable = false, length = 64, updatable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "LAST_USED", nullable = false, length = 64)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistentLogins that = (PersistentLogins) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getSeries() != null ? !getSeries().equals(that.getSeries()) : that.getSeries() != null) return false;
        if (getToken() != null ? !getToken().equals(that.getToken()) : that.getToken() != null) return false;
        return !(getLastUsed() != null ? !getLastUsed().equals(that.getLastUsed()) : that.getLastUsed() != null);
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getSeries() != null ? getSeries().hashCode() : 0);
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getLastUsed() != null ? getLastUsed().hashCode() : 0);
        return result;
    }
}
