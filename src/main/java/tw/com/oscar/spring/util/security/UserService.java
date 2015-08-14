/**
 * UserService.java
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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.service.account.AccountService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Title: UserService.java<br>
 * </p>
 * <strong>Description:</strong> A spring security framework's UserDetail implementation<br>
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
// @Component
public class UserService implements UserDetailsService {

    // @Log
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AccountService accountService;

    /**
     * A initialize method
     */
    @PostConstruct
    protected void initialize() {
        LOGGER.info("[Enter] UserService.initialize");
    }

    @Override
    // @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Login username : {}", username);
        Account account = accountService.findById(1L).orElseGet(Account::new); // TOTO
        if (null == account) {
            throw new UsernameNotFoundException("Cannot find specific account...");
        }

        // LOGGER.info("Password : " + BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()));
        return createUser(account);
    }

    public void signin(Account account) {
        SecurityContextHolder.getContext().setAuthentication(authentication(account));
    }

    private Authentication authentication(Account account) {
//        org.springframework.security.core.Authentication request = new UsernamePasswordAuthenticationToken(
//                this.authenticator.getUsername(), this.authenticator.getPassword());
        return new UsernamePasswordAuthenticationToken(createUser(account), null, createAuthority(account));
    }

    private User createUser(Account account) {
        return new User(account.getUsername(), account.getPassword(), account.isEnabled(),
                account.isAccountNonExpired(), account.isCredentialsNonExpired(),
                account.isAccountNonLocked(), createAuthority(account));
    }

    private Set<GrantedAuthority> createAuthority(Account account) {
        // AuthorityUtils.createAuthorityList(account.getRoles());
        Set<GrantedAuthority> authorities = new HashSet<>(account.getRoles().size());
        authorities.addAll(account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(toList()));
        return authorities;
    }
}
