/**
 * MailException
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
 * Title: MailException
 * </p>
 * <strong>Description:</strong> The abstract class for all other exception to extended <br>
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
public abstract class MailException extends Exception {

    /**
     * A constructor for MailException
     *
     * @param message the error message
     */
    public MailException(String message) {
        super(message);
    }

    /**
     * A constructor for MailException
     *
     * @param message the error message
     * @param cause   the Throwable object
     */
    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

}
