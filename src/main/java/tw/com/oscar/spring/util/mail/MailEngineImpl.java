/**
 * MailEngineImpl.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/2
 * <p>
 * H i s t o r y
 * 2015/07/15 Oscar Wei v2
 * + Modified genInternetAddressList() method for checking empty array or not
 * <p>
 * 2015/7/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail;

import com.google.common.collect.FluentIterable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.spring.util.mail.exception.OscarMailException;

import javax.mail.Authenticator;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * Title: MailEngineImpl.java
 * </p>
 * <strong>Description:</strong> A mail engine implementation <br>
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
public class MailEngineImpl implements MailEngine {

    private static final Logger logger = LoggerFactory.getLogger(MailEngineImpl.class);
    /**
     * The properties definition
     **/
    private static final String EMPTY = "";
    private static final String ERROR_MSG = "Illegal argument passed: ";

    /**
     * @param simpleMailInfo a SimpleMailInfo object
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     * @see tw.com.oscar.spring.util.mail.MailEngineImpl#send(SimpleMailInfo)
     */
    @Override
    public void send(SimpleMailInfo simpleMailInfo) throws OscarMailException {
        logger.info("" + simpleMailInfo);
        send(new SimpleMailInfo[] {simpleMailInfo});
    }

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
     * @see tw.com.oscar.spring.util.mail.MailEngineImpl#send(SimpleMailInfo...)
     */
    @Override
    public void send(SimpleMailInfo... simpleMailInfos) throws OscarMailException {
        sendMail(simpleMailInfos);
    }

    /**
     * A function method for sending email
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
     * @param simpleMailInfos list of SimpleMailInfo objects
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    private void sendMail(SimpleMailInfo... simpleMailInfos) throws OscarMailException {
        logger.info("[Enter] MailEngineImpl.sendMail");
        HtmlEmail email;
        try {
            for (SimpleMailInfo info : simpleMailInfos) {
                email = new HtmlEmail();
                email = setCommon(email, info);
                String sendMessage = email.send();
                logger.info("Send message: " + sendMessage);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OscarMailException(e.getMessage(), e);
        }
        logger.info("[Exit] MailEngineImpl.sendMail");
    }

    /**
     * A function method for setting common value to Email object
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
     * @param email a HtmlEmail object
     * @param info  a SimpleMailInfo object
     * @return the Email object
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    private HtmlEmail setCommon(HtmlEmail email, SimpleMailInfo info) throws OscarMailException {
        /** Process particular validation **/
        checkNotNull(email, ERROR_MSG + "Email object cannot be null");
        checkNotNull(info, ERROR_MSG + "SimpleMailInfo object cannot be null");
        checkNotNull(info.getServerInfo(), ERROR_MSG + "SimpleMailInfo.ServerInfo object cannot be null");
        checkNotNull(info.getFrom(), ERROR_MSG + "Email property 'from' cannot be null");
        checkNotNull(info.getTo(), ERROR_MSG + "Email property 'to' cannot be null");
        checkNotNull(info.getContent(), ERROR_MSG + "Email property 'content' cannot be null");
        checkNotNull(info.getServerInfo().getHost(), ERROR_MSG + "Cannot obtain valid hostname");
        /** A lock object **/
        Object lock = new Object();
        synchronized (lock) {
            email.setDebug(info.getDebug().orElse(false));
            String charset = info.getCharset().orElse(null);
            try {
                if (StringUtils.isNotBlank(charset)) {
                    email.setCharset(charset);
                }
                email.setSentDate(new Date());
                if (null != info.getSentDate()) {
                    email.setSentDate(info.getSentDate());
                }
                SimpleServerInfo serverInfo = info.getServerInfo();
                email.setSSLOnConnect(serverInfo.getSsl().orElse(false));
                email.setHostName(serverInfo.getHost());
                email.setSmtpPort(serverInfo.getPort().get());
                if (StringUtils.isNotBlank(serverInfo.getUsername())) {
                    Authenticator auth = new org.apache.commons.mail.DefaultAuthenticator(serverInfo.getUsername(),
                            serverInfo.getPassword().orElse(EMPTY));
                    email.setAuthenticator(auth);
                }
                email.setFrom(info.getFrom(), info.getFromBy().orElse(EMPTY));
                email.setSubject(info.getSubject());
                if (StringUtils.isNotBlank(info.getContent())) {
                    email.setTextMsg(info.getContent());
                }
                if (StringUtils.isNotBlank(info.getHtmlContent())) {
                    email.setHtmlMsg(info.getHtmlContent());
                }
                email.setTo(genInternetAddressList(info.getTo(), charset));
                /** Process CC, BCC, ReplyTo **/
                Collection<InternetAddress> mailAddress = genInternetAddressList(info.getCc(), charset);
                if (CollectionUtils.isNotEmpty(mailAddress)) {
                    email.setCc(mailAddress);
                }
                mailAddress = genInternetAddressList(info.getBcc(), charset);
                if (CollectionUtils.isNotEmpty(mailAddress)) {
                    email.setBcc(mailAddress);
                }
                mailAddress = genInternetAddressList(info.getReplyTo(), charset);
                if (CollectionUtils.isNotEmpty(mailAddress)) {
                    email.setReplyTo(mailAddress);
                }
                attach(email, info.getAttachment());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new OscarMailException(e.getMessage(), e);
            }
        }
        return email;
    }

    /**
     * A function method for generating InternetAddress objects by arguments
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
     * @param emailAddressList list of email address
     * @param charset          a charset name
     * @return list of InternetAddress objects
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    private Collection<InternetAddress> genInternetAddressList(String[] emailAddressList, final String charset)
            throws OscarMailException {
        if (!ArrayUtils.isEmpty(emailAddressList)) {
//            Collection<InternetAddress> intAddrs = new ArrayList<InternetAddress>();
//            for (String emailAddress : emailAddressList) {
//                intAddrs.add(genInternetAddress(emailAddress, EMPTY, charset));
//            }
//            return intAddrs;

            return FluentIterable.from(Arrays.asList(emailAddressList))
                    .filter(StringUtils::isNotBlank)
                    .transform(emailAddress -> {
                                try {
                                    return genInternetAddress(emailAddress, EMPTY, charset);
                                } catch (OscarMailException e) {
                                    throw new RuntimeException(e.getMessage(), e);
                                }
                            }
                    ).toList();
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * A function method for generating specific InternetAddress object by arguments
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
     * @param emailAddress a email address
     * @param username     a username
     * @param charset      a charset name
     * @return the InternetAddress object
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    private InternetAddress genInternetAddress(final String emailAddress, final String username, final String charset)
            throws OscarMailException {
        InternetAddress address;
        try {
            address = new InternetAddress(emailAddress);
            if (StringUtils.isNotEmpty(username)) {
                if (StringUtils.isEmpty(charset)) {
                    address.setPersonal(username);
                } else {
                    final Charset set = Charset.forName(charset);
                    address.setPersonal(username, set.name());
                }
            }
            address.validate();
        } catch (final AddressException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            throw new OscarMailException(e.getMessage(), e);
        }
        return address;
    }

    /**
     * A function method for attach files to email content
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
     * @param email      a MultiPartEmail object
     * @param attachment list of files should be attached to
     * @throws OscarMailException Throw exception when:<br>
     *                            If any exception occurred
     */
    private void attach(MultiPartEmail email, File[] attachment) throws OscarMailException {
        EmailAttachment attach;
        try {
            if (!ArrayUtils.isEmpty(attachment)) {
                for (File file : attachment) {
                    attach = new EmailAttachment();
                    attach.setDisposition(EmailAttachment.ATTACHMENT);
                    attach.setPath(file.getAbsolutePath());
                    attach.setName(FilenameUtils.getName(file.getAbsolutePath()));
                    email.attach(attach);
                }
            }
        } catch (EmailException e) {
            logger.error(e.getMessage(), e);
            throw new OscarMailException(e.getMessage(), e);
        }
    }
}
