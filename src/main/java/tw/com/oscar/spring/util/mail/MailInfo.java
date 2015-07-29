/**
 * MailInfo.java
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

import java.io.File;
import java.util.Date;
import java.util.Optional;

/**
 * <p>
 * Title: MailInfo.java
 * </p>
 * <strong>Description:</strong> A SMTP mail sending information interface <br>
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
interface MailInfo {
    
    /**
     * A enumeration for mail type
     */
    enum MailType {
        TEXT, HTML
    }
    
    /**
     * A setter for 'debug' property
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
     * @param debug a value for 'debug' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setDebug(Optional<Boolean> debug) throws OscarMailException;
    
    /**
     * A setter for 'serverInfo' property
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
     * @param serverInfo a value for 'serverInfo' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setServerInfo(SimpleServerInfo serverInfo) throws OscarMailException;
    
    /**
     * A setter for 'mailType' property
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
     * @param mailType a value for 'mailType' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setMailType(MailType mailType) throws OscarMailException;
    
    /**
     * A setter for 'charset' property
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
     * @param charset a value for 'charset' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setCharset(Optional<String> charset) throws OscarMailException;
    
    /**
     * A setter for 'from' property
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
     * @param from a value for 'from' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setFrom(String from) throws OscarMailException;
    
    /**
     * A setter for 'fromBy' property
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
     * @param fromBy a value for 'fromBy' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setFromBy(Optional<String> fromBy) throws OscarMailException;
    
    /**
     * A setter for 'replyTo' property
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
     * @param replyTo a value for 'replyTo' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setReplyTo(String replyTo) throws OscarMailException;
    
    /**
     * A setter for 'replyTo' property
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
     * @param replyTo a list values for 'replyTo' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setReplyTo(String[] replyTo) throws OscarMailException;
    
    /**
     * A setter for 'to' property
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
     * @param to a value for 'to' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setTo(String to) throws OscarMailException;
    
    /**
     * A setter for 'to' property
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
     * @param to a list values for 'to' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setTo(String[] to) throws OscarMailException;
    
    /**
     * A setter for 'cc' property
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
     * @param cc a value for 'cc' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setCc(String cc) throws OscarMailException;
    
    /**
     * A setter for 'cc' property
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
     * @param cc a list values for 'cc' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setCc(String[] cc) throws OscarMailException;
    
    /**
     * A setter for 'bcc' property
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
     * @param bcc a value for 'bcc' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setBcc(String bcc) throws OscarMailException;
    
    /**
     * A setter for 'bcc' property
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
     * @param bcc a list values for 'bcc' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setBcc(String[] bcc) throws OscarMailException;
    
    /**
     * A setter for 'sentDate' property
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
     * @param sentDate a value for 'sentDate' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setSentDate(Date sentDate) throws OscarMailException;
    
    /**
     * A setter for 'subject' property
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
     * @param subject a value for 'subject' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setSubject(String subject) throws OscarMailException;
    
    /**
     * A setter for 'content' property
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
     * @param content a value for 'content' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setContent(String content) throws OscarMailException;
    
    /**
     * A setter for 'htmlContent' property
     *
     * @param htmlContent a value for 'htmlContent' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    void setHtmlContent(String htmlContent) throws OscarMailException;
    
    /**
     * A setter for 'attachment' property
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
     * @param attachment a file for 'attachment' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setAttachment(File attachment) throws OscarMailException;
    
    /**
     * A setter for 'attachment' property
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
     * @param attachment a files for 'attachment' property
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    void setAttachment(File[] attachment) throws OscarMailException;
}
