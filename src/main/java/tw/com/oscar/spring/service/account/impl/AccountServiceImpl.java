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

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.oscar.spring.dao.account.AccountDAO;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.service.account.AccountService;
import tw.com.oscar.spring.util.annotations.Log;

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
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Log
    Logger LOGGER;

    @Autowired
    private AccountDAO accountDao;

    /**
     * @see AccountService#findAll() method
     */
    @Override
    public Stream<Account> findAll() {
        return accountDao.findAll().stream();
    }

    /**
     * @see AccountService#findById(Long) method
     */
    @Override
    public Optional<Account> findById(Long id) {
        LOGGER.info("[Enter] AccountServiceImpl.findById, id = {}", id);
        return Optional.ofNullable(accountDao.find(id));
    }

    /**
     * @see AccountService#findByLoadId(Long) method
     */
    @Override
    public Optional<Account> findByLoadId(Long id) {
        return null;
    }
}
