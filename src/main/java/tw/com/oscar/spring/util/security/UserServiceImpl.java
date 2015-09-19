/**
 * UserServiceImpl.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/6
 * <p>
 * H i s t o r y
 * 2015/8/6 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.security;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.service.account.AccountService;
import tw.com.oscar.spring.util.annotations.Log;

/**
 * <p>
 * Title: UserServiceImpl.java<br>
 * </p>
 * <strong>Description:</strong> A spring security framework's UserDetailsService implementation<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/6
 * @since 2015/8/6
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Log
    Logger LOGGER;

    @Autowired
    private AccountService accountService;

    /**
     * A method used for searching Account object by username
     *
     * @param username a username
     * @return a UserDetails object
     * @throws UsernameNotFoundException throw exception when:<br>if cannot find any Account object
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Login username : {}", username);
        Account account = this.accountService.findByUsername(username).get();
        if (null == account) {
            LOGGER.error("Cannot find any specific account...");
            throw new UsernameNotFoundException("Cannot find any specific account...");
        }

        LOGGER.info("Password : {}", account.getPassword());
        // LOGGER.info("Password : {}", BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        return new SecurityUser(account);
    }
    
}
