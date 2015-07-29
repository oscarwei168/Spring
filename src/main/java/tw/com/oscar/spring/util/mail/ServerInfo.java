/**
 * ServerInfo.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/2
 * <p>
 * H i s t o r y
 * 2015/7/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail;

import tw.com.oscar.spring.util.mail.exception.OscarMailException;

import java.util.Optional;

/**
 * <p>
 * Title: ServerInfo.java
 * </p>
 * <strong>Description:</strong> A SMTP server information interface <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/2
 * @since 2015/7/2
 */
public interface ServerInfo {
    
    /**
     * A setter for 'ssl' property
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
     * @param ssl a value for 'ssl' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setSsl(Optional<Boolean> ssl) throws OscarMailException;
    
    /**
     * A setter for 'host' property
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
     * @param host a value for 'host' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setHost(String host) throws OscarMailException;
    
    /**
     * A setter for 'port' property
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
     * @param port a value for 'port' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setPort(Optional<Integer> port) throws OscarMailException;
    
    /**
     * A setter for 'username' property
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
     * @param username a value for 'username' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setUsername(String username) throws OscarMailException;
    
    /**
     * A setter for 'password' property
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
     * @param password a value for 'password' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setPassword(Optional<String> password) throws OscarMailException;
}
