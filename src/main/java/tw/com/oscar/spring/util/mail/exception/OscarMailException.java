/**
 * OscarMailException
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, Oscar Wei Inc.
 *
 * @author Oscar Wei
 * @since 2015/7/1
 * <p>
 * H i s t o r y
 * 2015/7/1 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail.exception;

/**
 * <p>
 * Title: OscarMailException
 * </p>
 * <strong>Description:</strong> The main mail exception class <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Oscar Wei Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/1
 * @since 2015/7/1
 */
public class OscarMailException extends MailException {

    /**
     * A constructor for OscarMailException
     *
     * @param message the error message
     */
    public OscarMailException(String message) {
        super(message);
    }

    /**
     * A constructor for OscarMailException
     *
     * @param message the error message
     * @param cause   the Throwable object
     */
    public OscarMailException(String message, Throwable cause) {
        super(message, cause);
    }

}
