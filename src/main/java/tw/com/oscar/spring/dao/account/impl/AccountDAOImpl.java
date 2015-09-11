/**
 * AccountDAO.java
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
package tw.com.oscar.spring.dao.account.impl;

import org.springframework.stereotype.Repository;
import tw.com.oscar.spring.dao.account.AccountDAO;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.util.common.dao.BaseDAO;

/**
 * <p>
 * Title: AccountDAO.java<br>
 * </p>
 * <strong>Description:</strong> A Account DAO concrete implementation<br>
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
@Repository
public class AccountDAOImpl extends BaseDAO<Account, Long> implements AccountDAO {
}
