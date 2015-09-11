/**
 * MailConfig.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/3
 * <p>
 * H i s t o r y
 * <p>
 * 2015/7/3 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import tw.com.oscar.spring.util.mail.SimpleMailInfo;
import tw.com.oscar.spring.util.mail.SimpleServerInfo;
import tw.com.oscar.spring.util.mail.exception.OscarMailException;

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * <p>
 * Title: MailConfig.java
 * </p>
 * <strong>Description:</strong> A mail configuration bean <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/3
 * @since 2015/7/3
 */
@Configuration
public class MailConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfig.class);

    @Value("${smtp.host}")
    private String host;
    @Value("${smtp.port}")
    private int port;
    @Value("${smtp.username}")
    private String username;
    @Value("${smtp.password}")
    private String password;

    /**
     * A bean for obtain Mail SMTP server information
     *
     * @return the ServerInfo object
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Bean
    @Description("A SMTP server information bean")
    public SimpleServerInfo getServerInfo() throws OscarMailException {
        Objects.requireNonNull(host);
        SimpleServerInfo serverInfo = new SimpleServerInfo();
        serverInfo.setHost(host);
        serverInfo.setPort(Optional.of(port));
        serverInfo.setUsername(username);
        serverInfo.setPassword(Optional.ofNullable(password));
        LOGGER.info(Objects.toString(serverInfo));
        return serverInfo;
    }

    /**
     * A bean for obtain simple mail information object
     *
     * @param serverInfo a simple SMTP server info object
     * @return the MailInfo object
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Bean(destroyMethod = "close")
    @Scope(value = "prototype")
    @Autowired
    @Description("A mail info bean that has always contains SMTP server info")
    public SimpleMailInfo getMailInfo(SimpleServerInfo serverInfo) throws OscarMailException {
        Objects.requireNonNull(serverInfo);
        SimpleMailInfo simpleMailInfo = new SimpleMailInfo();
        simpleMailInfo.setServerInfo(serverInfo);
        return simpleMailInfo;
    }

    /**
     * A bean for spring java mail configure
     *
     * @return a JavaMailSenderImpl object
     */
    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(465);
        javaMailSender.setProtocol("smtps");
        javaMailSender.setUsername("oscarwei168@gmail.com");
        javaMailSender.setPassword("");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }
}
