/**
 * AccountDAOImplTest.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/1
 * <p>
 * H i s t o r y
 * 2015/8/1 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.test.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import tw.com.oscar.spring.dao.account.AccountDAO;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.test.EntityDAOImplTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * <p>
 * Title: AccountDAOImplTest.java<br>
 * </p>
 * <strong>Description:</strong> A AccountController unit testing<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/1
 * @since 2015/8/1
 */
public class AccountDAOImplTest extends EntityDAOImplTest {

    @Autowired
    AccountDAO accountDAO;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] dataSets = new IDataSet[] {
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream
                        ("accounts.xml"))};
        return new CompositeDataSet(dataSets);
    }

    @Test
    public void find() {
        assertNotNull(accountDAO.find(1L));
        assertNull(accountDAO.find(100L));
    }

    @Test
    public void saveAccount() {
        accountDAO.save(getSimpleAccount());
        assertEquals(accountDAO.findAll().size(), 3);
    }

    private Account getSimpleAccount() {
        Account account = new Account();
        account.setId(3L);
        account.setUsername("sunnywei168");
        account.setPassword("password");
        account.setFirstName("Sunny");
        account.setLastName("Wei");
        account.setEmail("sunnywei168@msn.com");
        account.setSalary(new BigDecimal(100000));
        account.setUserCreated("Admin");
        account.setDateCreated(new Date());
        return account;
    }

}
