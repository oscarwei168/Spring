/**
 * AccountDao.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/27
 * <p>
 * H i s t o r y
 * 2015/7/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.function.testcase.dao;

import tw.com.oscar.spring.domain.Account;

import java.util.stream.Stream;

/**
 * <p>
 * Title: AccountDao.java<br>
 * </p>
 * <strong>Description:</strong> A account dao definition<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/27
 * @since 2015/7/27
 */
public interface AccountDao extends com.googlecode.genericdao.dao.hibernate.GenericDAO<Account, Long> {

    Stream<Account> findByName(String name);
}
