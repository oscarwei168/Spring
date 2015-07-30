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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import tw.com.oscar.spring.domain.commons.VersionEntity;
import tw.com.oscar.spring.domain.enums.Gender;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;

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
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                })
        })
public class Account extends VersionEntity {

    public static final String SQL_ACCOUNT_FIND_BY_EMAIL = "accountFindByEmail";

    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender = Gender.MALE;
    private String email;
    private BigDecimal salary;
    private BigDecimal yearEndBonus;
    private Blob photo;
    private Clob description;

    public Account() {
    }

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true, updatable = false)
    @NotNull
    @Size(min = 5, max = 50)
    @NaturalId
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Analyzer(definition = "customanalyzer")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (getUsername() != null ? !getUsername().equals(account.getUsername()) : account.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(account.getPassword()) : account.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(account.getFirstName()) : account.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(account.getLastName()) : account.getLastName() != null)
            return false;
        if (getGender() != account.getGender()) return false;
        if (getEmail() != null ? !getEmail().equals(account.getEmail()) : account.getEmail() != null) return false;
        if (getSalary() != null ? !getSalary().equals(account.getSalary()) : account.getSalary() != null) return false;
        if (getYearEndBonus() != null ? !getYearEndBonus().equals(account.getYearEndBonus()) : account.getYearEndBonus() != null)
            return false;
        if (getPhoto() != null ? !getPhoto().equals(account.getPhoto()) : account.getPhoto() != null) return false;
        return !(getDescription() != null ? !getDescription().equals(account.getDescription()) : account.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getSalary() != null ? getSalary().hashCode() : 0);
        result = 31 * result + (getYearEndBonus() != null ? getYearEndBonus().hashCode() : 0);
        result = 31 * result + (getPhoto() != null ? getPhoto().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
