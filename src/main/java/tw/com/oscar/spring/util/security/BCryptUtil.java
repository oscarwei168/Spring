/**
 * BCryptUtil.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/7
 * <p>
 * H i s t o r y
 * 2015/8/7 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * Title: BCryptUtil.java<br>
 * </p>
 * <strong>Description:</strong> A password encoder utilities <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/7
 * @since 2015/8/7
 */
public class BCryptUtil {

    /**
     * A main method for testing
     *
     * @param args array of arguments
     */
    public static void main(String[] args) {
        BCryptUtil instance = instance();
        System.out.println(instance.encodePassword("password"));
    }

    private static BCryptUtil instance = new BCryptUtil();

    /**
     * A private constructor
     */
    private BCryptUtil() {
    }

    /**
     * A method for obtain this instance
     *
     * @return a BCryptUtil object
     */
    public static BCryptUtil instance() {
        return instance;
    }

    /**
     * A method for generating encrypted string
     *
     * @param password a string that want to encrypted
     * @return a encrypted value
     */
    public String encodePassword(final String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * A method for validating raw string with encrypted one
     *
     * @param rawPassword     a raw password
     * @param encodedPassword a encrypted password
     * @return true if validate success, or false otherwise
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
