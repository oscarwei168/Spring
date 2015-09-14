/**
 * AccountServiceImpl.java
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
package tw.com.oscar.spring.service.account.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.oscar.spring.dao.account.AccountDAO;
import tw.com.oscar.spring.dao.account.AccountLoginAttemptDAO;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.domain.AccountLoginAttempt;
import tw.com.oscar.spring.service.account.AccountService;
import tw.com.oscar.spring.util.annotations.Log;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: AccountServiceImpl.java<br>
 * </p>
 * <strong>Description:</strong> A entity service concrete implementation <br>
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
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Log
    Logger LOGGER;

    @Autowired
    private AccountDAO accountDao;

    @Autowired
    private AccountLoginAttemptDAO accountLoginAttemptDao;

    /**
     * @see AccountService#findAll() method
     */
    @Override
    @Transactional(readOnly = true)
    public Stream<Account> findAll() {
        return accountDao.findAll().stream();
    }

    /**
     * @see AccountService#findById(Long) method
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(final Long id) {
        return Optional.ofNullable(accountDao.find(id));
    }

    /**
     * @see AccountService#findByLoadId(Long) method
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByLoadId(final Long id) {
        return Optional.ofNullable(accountDao.getReference(id));
    }

    /**
     * @see AccountService#findByUsername(String) method
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByUsername(String username) throws UsernameNotFoundException {
        Search search = new Search(Account.class);
        Filter filter = new Filter("username", username, Filter.OP_EQUAL);
        search.addFilter(filter);
        SearchResult<Account> result = accountDao.searchAndCount(search);
        List<Account> accountList = result.getResult();
        int count = result.getTotalCount();
        if (1 == count) {
            return Optional.of(accountList.get(0));
        }
        throw new UsernameNotFoundException("Cannot find anyone or multiple result...");
    }

    /**
     * @see AccountService#save(Account) method
     */
    @Override
    public void save(Account entity) {
        accountDao.save(entity);
    }

    /**
     * @see AccountService#save(AccountLoginAttempt) method
     */
    @Override
    public void save(AccountLoginAttempt entity) {
        accountLoginAttemptDao.save(entity);
    }
}
