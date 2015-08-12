/**
 * AccountNotFoundException.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/8
 * <p>
 * H i s t o r y
 * 2015/8/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.exception;

/**
 * <p>
 * Title: AccountNotFoundException.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/8
 * @since 2015/8/8
 */
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
