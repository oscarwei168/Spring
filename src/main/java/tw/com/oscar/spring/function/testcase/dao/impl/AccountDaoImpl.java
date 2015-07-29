/**
 * AccountDaoImpl.java
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
package tw.com.oscar.spring.function.testcase.dao.impl;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.function.testcase.dao.AccountDao;

import java.util.stream.Stream;

/**
 * <p>
 * Title: AccountDaoImpl.java<br>
 * </p>
 * <strong>Description:</strong> A account dao concrete implementation<br>
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
public class AccountDaoImpl extends GenericDAOImpl<Account, Long> implements AccountDao {

    @Override
    public Stream<Account> findByName(String name) {
        Criteria criteria = getSession().createCriteria(Account.class);
        criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return criteria.list().stream();
    }


//    @Override
//    @Cacheable("test-cache")
//    public Stream<Account> findAll() {
//        System.out.println("TEST...");
//        return getCurrentSession().createCriteria(Account.class).list().stream();
//    }
//
//    @Override
//    @Cacheable(value = "test-cache", key = "#pid")
//    public Optional<Account> findByPid(Long pid) {
//        return Optional.ofNullable(null);
//    }
//
//    @Override
//    @Cacheable(value = "test-cache", key = "#pid")
//    public Optional<Account> findByLoadPid(Long pid) {
//        return null;
//    }
}
