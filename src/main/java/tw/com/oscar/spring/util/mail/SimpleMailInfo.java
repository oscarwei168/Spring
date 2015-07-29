/**
 * SimpleMailInfo.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/2
 * <p>
 * H i s t o r y
 * 2015/07/15 Oscar Wei v2
 * + Modified property 'fromBy' have default value
 * <p>
 * 2015/7/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail;

import com.google.common.base.MoreObjects;
import tw.com.oscar.spring.util.mail.exception.OscarMailException;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * Title: SimpleMailInfo.java
 * </p>
 * <strong>Description:</strong> A SMTP mail sending information implementation <br>
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
public class SimpleMailInfo implements MailInfo, Serializable {
    
    /**
     * The properties definition
     **/
    private Optional<Boolean> debug = Optional.of(false);
    private SimpleServerInfo serverInfo;
    private MailType mailType = MailType.HTML;
    private Optional<String> charset = Optional.ofNullable("UTF-8");
    private String from;
    private Optional<String> fromBy = Optional.empty();
    private String[] replyTo;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private Date sentDate;
    private String subject;
    private String content;
    private String htmlContent;
    private File[] attachment;
    
    /**
     * A default constructor
     */
    public SimpleMailInfo() {
    }
    
    /**
     * A constructor for SimpleMailInfo
     *
     * @param original a original SimpleMailInfo object
     */
    public SimpleMailInfo(SimpleMailInfo original) {
        checkNotNull(original, "The original message argument cannot be null");
        this.debug = original.getDebug();
        this.serverInfo = original.getServerInfo();
        this.mailType = original.getMailType();
        this.charset = original.getCharset();
        this.from = original.getFrom();
        this.fromBy = original.getFromBy();
        this.replyTo = original.getReplyTo();
        if (null != original.getTo()) {
            this.to = copy(original.getTo());
        }
        if (null != original.getCc()) {
            this.cc = copy(original.getCc());
        }
        if (null != original.getBcc()) {
            this.bcc = copy(original.getBcc());
        }
        this.sentDate = original.getSentDate();
        this.subject = original.getSubject();
        this.content = original.getContent();
        this.attachment = original.getAttachment();
    }
    
    /**
     * A setter for 'debug' property
     *
     * @param debug a value for 'debug' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setDebug(Optional<Boolean> debug) throws OscarMailException {
        this.debug = debug;
    }
    
    /**
     * A getter for 'debug' property
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
     * @return the 'debug' property value
     */
    public Optional<Boolean> getDebug() {
        return debug;
    }
    
    /**
     * A setter for 'debug' property
     *
     * @param serverInfo a value for 'serverInfo' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setServerInfo(SimpleServerInfo serverInfo) throws OscarMailException {
        this.serverInfo = serverInfo;
    }
    
    /**
     * A getter for 'mailType' property
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
     * @return the 'mailType' property value
     */
    public SimpleServerInfo getServerInfo() {
        return serverInfo;
    }
    
    /**
     * A setter for 'mailType' property
     *
     * @param mailType a value for 'mailType' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setMailType(MailType mailType) throws OscarMailException {
        this.mailType = mailType;
    }
    
    /**
     * A getter for 'mailType' property
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
     * @return the 'mailType' property value
     */
    public MailType getMailType() {
        return mailType;
    }
    
    /**
     * A setter for 'charset' property
     *
     * @param charset a value for 'charset' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setCharset(Optional<String> charset) throws OscarMailException {
        this.charset = charset;
    }
    
    /**
     * A getter for 'charset' property
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
     * @return the 'charset' property value
     */
    public Optional<String> getCharset() {
        return charset;
    }
    
    /**
     * A setter for 'from' property
     *
     * @param from a value for 'from' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setFrom(String from) throws OscarMailException {
        this.from = from;
    }
    
    /**
     * A getter for 'from' property
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
     * @return the 'from' property value
     */
    public String getFrom() {
        return this.from;
    }
    
    /**
     * A setter for 'fromBy' property
     *
     * @param fromBy a value for 'fromBy' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setFromBy(Optional<String> fromBy) throws OscarMailException {
        this.fromBy = fromBy;
    }
    
    /**
     * A getter for 'fromBy' property
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
     * @return the 'fromBy' property value
     */
    public Optional<String> getFromBy() {
        return this.fromBy;
    }
    
    /**
     * A setter for 'replyTo' property
     *
     * @param replyTo a value for 'replyTo' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setReplyTo(String replyTo) throws OscarMailException {
        this.replyTo = new String[] {replyTo};
    }
    
    /**
     * A setter for 'replyTo' property
     *
     * @param replyTo a value for 'replyTo' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setReplyTo(String[] replyTo) throws OscarMailException {
        this.replyTo = replyTo;
    }
    
    /**
     * A getter for 'replyTo' property
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
     * @return the 'replyTo' property value
     */
    public String[] getReplyTo() {
        return replyTo;
    }
    
    /**
     * A setter for 'to' property
     *
     * @param to a value for 'to' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setTo(String to) throws OscarMailException {
        this.to = new String[] {to};
    }
    
    /**
     * A setter for 'to' property
     *
     * @param to a value for 'to' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setTo(String[] to) throws OscarMailException {
        this.to = to;
    }
    
    /**
     * A getter for 'to' property
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
     * @return the 'to' property value
     */
    public String[] getTo() {
        return this.to;
    }
    
    /**
     * A setter for 'cc' property
     *
     * @param cc a value for 'cc' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    public void setCc(String cc) throws OscarMailException {
        this.cc = new String[] {cc};
    }
    
    /**
     * A setter for 'cc' property
     *
     * @param cc a value for 'cc' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setCc(String[] cc) throws OscarMailException {
        this.cc = cc;
    }
    
    /**
     * A getter for 'cc' property
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
     * @return the 'cc' property value
     */
    public String[] getCc() {
        return cc;
    }
    
    /**
     * A setter for 'bcc' property
     *
     * @param bcc a value for 'bcc' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setBcc(String bcc) throws OscarMailException {
        this.bcc = new String[] {bcc};
    }
    
    /**
     * A setter for 'bcc' property
     *
     * @param bcc a value for 'bcc' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setBcc(String[] bcc) throws OscarMailException {
        this.bcc = bcc;
    }
    
    /**
     * A getter for 'bcc' property
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
     * @return the 'bcc' property value
     */
    public String[] getBcc() {
        return bcc;
    }
    
    /**
     * A setter for 'sentDate' property
     *
     * @param sentDate a value for 'sentDate' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setSentDate(Date sentDate) throws OscarMailException {
        this.sentDate = sentDate;
    }
    
    /**
     * A getter for 'sentDate' property
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
     * @return the 'sentDate' property value
     */
    public Date getSentDate() {
        return sentDate;
    }
    
    /**
     * A setter for 'subject' property
     *
     * @param subject a value for 'subject' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setSubject(String subject) throws OscarMailException {
        this.subject = subject;
    }
    
    /**
     * A getter for 'subject' property
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
     * @return the 'subject' property value
     */
    public String getSubject() {
        return this.subject;
    }
    
    /**
     * A setter for 'content' property
     *
     * @param content a value for 'content' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setContent(String content) throws OscarMailException {
        this.content = content;
    }
    
    /**
     * A getter for 'content' property
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
     * @return the 'content' property value
     */
    public String getContent() {
        return this.content;
    }
    
    /**
     * A setter for 'htmlContent' property
     *
     * @param htmlContent a value for 'htmlContent' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setHtmlContent(String htmlContent) throws OscarMailException {
        this.htmlContent = htmlContent;
    }
    
    /**
     * A getter for 'htmlContent' property
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
     * @return the 'htmlContent' property value
     */
    public String getHtmlContent() {
        return this.htmlContent;
    }
    
    /**
     * A setter for 'attachment' property
     *
     * @param attachment a value for 'attachment' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setAttachment(File attachment) throws OscarMailException {
        this.attachment = new File[] {attachment};
    }
    
    /**
     * A setter for 'attachment' property
     *
     * @param attachment a value for 'attachment' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setAttachment(File[] attachment) throws OscarMailException {
        this.attachment = attachment;
    }
    
    /**
     * A getter method for property 'attachment' value
     *
     * @return the property 'attachment' value
     */
    public File[] getAttachment() {
        return attachment;
    }
    
    /**
     * A equals() method override implementation
     *
     * @param o a object
     * @return true if equals, or false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        SimpleMailInfo that = (SimpleMailInfo) o;
        
        if (getDebug() != null ? !getDebug().equals(that.getDebug()) : that.getDebug() != null) return false;
        if (getServerInfo() != null ? !getServerInfo().equals(that.getServerInfo()) : that.getServerInfo() != null)
            return false;
        if (getMailType() != that.getMailType()) return false;
        if (getCharset() != null ? !getCharset().equals(that.getCharset()) : that.getCharset() != null) return false;
        if (getFrom() != null ? !getFrom().equals(that.getFrom()) : that.getFrom() != null) return false;
        if (getFromBy() != null ? !getFromBy().equals(that.getFromBy()) : that.getFromBy() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getReplyTo(), that.getReplyTo())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getTo(), that.getTo())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getCc(), that.getCc())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getBcc(), that.getBcc())) return false;
        if (getSentDate() != null ? !getSentDate().equals(that.getSentDate()) : that.getSentDate() != null)
            return false;
        if (getSubject() != null ? !getSubject().equals(that.getSubject()) : that.getSubject() != null) return false;
        if (getContent() != null ? !getContent().equals(that.getContent()) : that.getContent() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getAttachment(), that.getAttachment());
        
    }
    
    /**
     * A hashCode() method override implementation
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        int result = getDebug() != null ? getDebug().hashCode() : 0;
        result = 31 * result + (getServerInfo() != null ? getServerInfo().hashCode() : 0);
        result = 31 * result + (getMailType() != null ? getMailType().hashCode() : 0);
        result = 31 * result + (getCharset() != null ? getCharset().hashCode() : 0);
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getFromBy() != null ? getFromBy().hashCode() : 0);
        result = 31 * result + (getReplyTo() != null ? Arrays.hashCode(getReplyTo()) : 0);
        result = 31 * result + (getTo() != null ? Arrays.hashCode(getTo()) : 0);
        result = 31 * result + (getCc() != null ? Arrays.hashCode(getCc()) : 0);
        result = 31 * result + (getBcc() != null ? Arrays.hashCode(getBcc()) : 0);
        result = 31 * result + (getSentDate() != null ? getSentDate().hashCode() : 0);
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getAttachment() != null ? Arrays.hashCode(getAttachment()) : 0);
        return result;
    }
    
    /**
     * A toString() method override implementation
     *
     * @return a string
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("debug", debug)
                .add("serverInfo", serverInfo)
                .add("mailType", mailType)
                .add("charset", charset)
                .add("from", from)
                .add("fromBy", fromBy)
                .add("replyTo", replyTo)
                .add("to", to)
                .add("cc", cc)
                .add("bcc", bcc)
                .add("sentDate", sentDate)
                .add("subject", subject)
                .add("content", content)
                .add("attachment", attachment)
                .toString();
    }
    
    /**
     * A function method for cloning argument object
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
     * @param state a original array
     * @return the clone array
     */
    private static String[] copy(String[] state) {
        String[] copy = new String[state.length];
        System.arraycopy(state, 0, copy, 0, state.length);
        return copy;
    }
}
