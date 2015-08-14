/**
 * AccountLoginAttempt.java
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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: AccountLoginAttempt.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
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
@Table(name = "ACCOUNT_LOGIN_ATTEMPT")
public class AccountLoginAttempt implements Serializable {

    private Long id;
    private Integer counts;
    private Date dateCreated;
    private Date dateLastModified;

    private Account account;

    @Id
    @GeneratedValue(generator = "accountForeignGenerator")
    @Column(name = "ID", nullable = false)
    @org.hibernate.annotations.GenericGenerator(name = "accountForeignGenerator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "account"))
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "COUNTS", nullable = false, precision = 1)
    public Integer getCounts() {
        return counts;
    }
    
    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    @Column(name = "DATE_CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "DATE_LAST_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }
    
    @OneToOne(mappedBy = "accountLoginAttempt")
    @PrimaryKeyJoinColumn
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
