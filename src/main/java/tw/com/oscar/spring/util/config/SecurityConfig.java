/**
 * SecurityConfig.java
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
package tw.com.oscar.spring.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import tw.com.oscar.spring.util.security.UserService;

/**
 * <p>
 * Title: SecurityConfig.java<br>
 * </p>
 * <strong>Description:</strong> A spring security framework configuration<br>
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
@Configuration
@EnableWebMvcSecurity
//@ImportResource(value = "classpath:spring-security-context.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * A bean for obtain UserService object
     *
     * @return a UserService object
     */
    @Bean
    public UserService userService() {
        return new UserService();
    }

    /**
     * A bean for obtain TokenBasedRememberMeServices object
     *
     * @return a TokenBasedRememberMeServices object
     */
    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", userService());
    }

    /**
     * A bean for obtain password encoder object
     *
     * @return a PasswordEncoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    /**
     * A configuration for setting authentication and password encoding strategy
     *
     * @param auth a AuthenticationManagerBuilder object
     * @throws Exception throw exception when:<br>
     *                   <ul><li>if cannot find any account info</li></ul>
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true).userDetailsService(userService()).passwordEncoder(passwordEncoder());
    }

    /**
     * A configuration for uri mapping pattern
     *
     * @param http HttpSecurity object
     * @throws Exception throw exception when:<br>
     *                   <ul><li>if something wrong</li></ul>
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/index", "/favicon.ico", "/resources/**", "/signup").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/signin").permitAll().failureUrl
                ("/signin?error=1").loginProcessingUrl("/authenticate").and().logout().logoutUrl("/logout").permitAll
                ().logoutSuccessUrl("/signin?logout").and().rememberMe().rememberMeServices(rememberMeServices()).key("remember-me-key");
    }
}
