/**
 * AccountVO.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/8
 * <p>
 * H i s t o r y
 * 2015/8/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.pojo.vo;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

/**
 * <p>
 * Title: AccountVO.java<br>
 * </p>
 * <strong>Description:</strong> A value object of Account<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/8
 * @since 2015/8/8
 */
public class AccountVO {

    private String username;
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("salary", salary)
                .toString();
    }
}
