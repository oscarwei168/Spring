/**
 * AccountService.java
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
package tw.com.oscar.spring.function.testcase.service;

import tw.com.oscar.spring.domain.Account;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: AccountService.java<br>
 * </p>
 * <strong>Description:</strong> A account service definitions <br>
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
public interface AccountService {

    /**
     * A method for searching all Account objects
     *
     * @return a stream that contains Account objects
     */
    Stream findAll();

    /**
     * A method for searching Account object by pid
     *
     * @param pid a pid
     * @return a Account object
     */
    Optional<Account> findByPid(Long pid);

    /**
     * A method for searching Account object by pid
     *
     * @param pid a pid
     * @return a Account object
     */
    Optional<Account> findByLoadPid(Long pid);
}
