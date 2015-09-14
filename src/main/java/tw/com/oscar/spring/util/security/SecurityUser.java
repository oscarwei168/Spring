/**
 * AuthenticatedUser.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/9/13
 * <p>
 * H i s t o r y
 * 2015/9/13 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tw.com.oscar.spring.domain.Account;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Title: AuthenticatedUser.java<br>
 * </p>
 * <strong>Description:</strong> A class extended by spring security UserDetails object<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/9/13
 * @since 2015/9/13
 */
public class SecurityUser extends Account implements UserDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * A default constructor
     */
    public SecurityUser() {
    }

    /**
     * A constructor for AuthenticatedUser object
     *
     * @param account a Account object
     */
    public SecurityUser(Account account) {
        if (null != account) {
            this.setId(account.getId());
            this.setFirstName(account.getFirstName());
            this.setLastName(account.getLastName());
            this.setUsername(account.getUsername());
            this.setPassword(account.getPassword());
            this.setEmail(account.getEmail());
            this.setEnabled(account.isEnabled());
            this.setAccountNonExpired(account.isAccountNonExpired());
            this.setAccountNonLocked(account.isAccountNonLocked());
            this.setCredentialsNonExpired(account.isCredentialsNonExpired());
            this.setRoles(account.getRoles());
        }
    }

    /**
     * A method used for obtaining authorities by account object
     *
     * @return list of GrantedAuthority objects
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>(this.getRoles().size());
        authorities.addAll(this.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(toList()));
        return authorities;
    }
}
