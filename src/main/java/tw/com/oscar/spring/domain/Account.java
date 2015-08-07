/**
 * Account.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/30
 * <p>
 * H i s t o r y
 * 2015/7/30 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.domain;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.Email;
import tw.com.oscar.spring.domain.commons.VersionEntity;
import tw.com.oscar.spring.domain.enums.Gender;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Title: Account.java<br>
 * </p>
 * <strong>Description:</strong> A Account domain object <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/30
 * @since 2015/7/30
 */
@Entity
@Table(name = "ACCOUNT", uniqueConstraints = @UniqueConstraint(name = "UK_USERNAME", columnNames
        = {"USERNAME"}), indexes = {@Index(name = "INX_USERNAME", columnList = "USERNAME",
        unique = true), @Index(name = "INX_EMAIL", columnList = "EMAIL")})
@BatchSize(size = 5)
@DynamicInsert
@DynamicUpdate
@Where(clause = "1 = 1")
@NamedQuery(name = Account.SQL_ACCOUNT_FIND_BY_EMAIL, query = "FROM Account a WHERE a.email = :email")
@Indexed
@AnalyzerDef(name = "accountAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @org.hibernate.search.annotations.Parameter(name = "language", value = "English")
                })
        })
public class Account extends VersionEntity {

    public static final String SQL_ACCOUNT_FIND_BY_EMAIL = "accountFindByEmail";

    private String username;
    // @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender = Gender.MALE;
    private LocalDate birthday;
    private String email;
    private BigDecimal salary;
    private BigDecimal yearEndBonus;
    private Blob photo;
    private Clob description;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private AccountLoginAttempt accountLoginAttempt;
    private Set<Role> roles = new HashSet<Role>(0);

    public Account() {
    }

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true, updatable = false)
    @NotNull
    @Size(min = 5, max = 50)
    @NaturalId
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Analyzer(definition = "accountAnalyzer")
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = 100)
    @NotNull
    @Size(min = 8, max = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    @NotNull
    @Size(max = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false, length = 30)
    @NotNull
    @Size(max = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "GENDER", nullable = false, length = 1)
    @Enumerated(EnumType.ORDINAL)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "BIRTHDAY")
    @Type(type = "localDateType")
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column(name = "EMAIL", nullable = false, length = 100)
    @Email
    @Size(max = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "SALARY", nullable = false, scale = 2)
    @Digits(integer = 8, fraction = 2)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Formula("SALARY * 5.6")
    public BigDecimal getYearEndBonus() {
        return yearEndBonus;
    }

    private void setYearEndBonus(BigDecimal yearEndBonus) {
        this.yearEndBonus = yearEndBonus;
    }

    @Lob
    @Column(name = "PHOTO")
    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    @Lob
    @Column(name = "DESCRIPTION")
    public Clob getDescription() {
        return description;
    }

    public void setDescription(Clob description) {
        this.description = description;
    }

    @Column(name = "ENABLED", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public AccountLoginAttempt getAccountLoginAttempt() {
        return accountLoginAttempt;
    }

    public void setAccountLoginAttempt(AccountLoginAttempt accountLoginAttempt) {
        this.accountLoginAttempt = accountLoginAttempt;
    }

    @ManyToMany(mappedBy = "accounts")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ForeignKey(name = "FK_ACCOUNT_ROLE")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
