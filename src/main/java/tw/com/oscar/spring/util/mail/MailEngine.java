/**
 * MailEngine
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/1
 * <p>
 * H i s t o r y
 * 2015/7/1 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail;

import tw.com.oscar.spring.util.mail.exception.OscarMailException;

/**
 * <p>
 * Title: MailEngine
 * </p>
 * <strong>Description:</strong> A mail engine service interfaces <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/1
 * @since 2015/7/1
 */
public interface MailEngine {

    /**
     * A function method for sending mail according to argument
     * <p>
     * <strong>Assume:</strong>
     * </p>
     * none
     * <p>
     * <strong>Precondition:</strong>
     * </p>
     * none
     * <p>
     * <strong>Postcondition:</strong>
     * </p>
     * none
     *
     * @param simpleMailInfo a SimpleMailInfo object
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void send(SimpleMailInfo simpleMailInfo) throws OscarMailException;

    /**
     * A function method for sending some mails according to argument
     * <p>
     * <strong>Assume:</strong>
     * </p>
     * none
     * <p>
     * <strong>Precondition:</strong>
     * </p>
     * none
     * <p>
     * <strong>Postcondition:</strong>
     * </p>
     * none
     *
     * @param simpleMailInfos array of SimpleMailInfo objects
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void send(SimpleMailInfo... simpleMailInfos) throws OscarMailException;
}
