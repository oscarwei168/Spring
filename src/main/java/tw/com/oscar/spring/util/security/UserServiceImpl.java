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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.service.account.AccountService;
import tw.com.oscar.spring.util.annotations.Log;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;

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

    /**
     * A method used for generating User object by Account object
     *
     * @param account a Account object
     * @return a spring security specific User object
     */
    private User createUser(Account account) {
        return new User(account.getUsername(), account.getPassword(), account.isEnabled(),
                account.isAccountNonExpired(), account.isCredentialsNonExpired(),
                account.isAccountNonLocked(), createAuthority(account));
    }

    /**
     * A method used for obtaining GrantedAuthority object by Account roles
     *
     * @param account a Account object
     * @return list of GrantedAuthority objects
     */
    private Set<GrantedAuthority> createAuthority(Account account) {
        // AuthorityUtils.createAuthorityList(account.getRoles());
        Set<GrantedAuthority> authorities = new HashSet<>(account.getRoles().size());
        authorities.addAll(account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(toList()));
        return authorities;
    }
}
