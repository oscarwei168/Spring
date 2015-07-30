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
package tw.com.oscar.spring.function.testcase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.function.testcase.dao.AccountDao;
import tw.com.oscar.spring.function.testcase.service.AccountService;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: AccountServiceImpl.java<br>
 * </p>
 * <strong>Description:</strong> A account service concrete implementation <br>
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
@Service("accountService")
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * @see AccountService#findAll() method
     */
    @Override
    public Stream<Account> findAll() {
        return accountDao.findAll().stream();
    }

    /**
     * @see AccountService#findByPid(Long) method
     */
    @Override
    public Optional<Account> findByPid(Long pid) {
        return Optional.ofNullable(accountDao.find(pid));
    }

    /**
     * @see AccountService#findByLoadPid(Long) method
     */
    @Override
    public Optional<Account> findByLoadPid(Long pid) {
        return null;
    }
}
