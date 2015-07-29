/**
 * AppConfig.java
 * Title: Oscar Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/25
 * <p>
 * H i s t o r y
 * 2015/7/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import tw.com.oscar.spring.Application;
import tw.com.oscar.spring.util.aspect.LoggingBeanPostProcessor;

/**
 * <p>
 * Title: AppConfig.java
 * </p>
 * <strong>Description:</strong> A class for configuration setting of spring framework<br>
 * This function include: - <br>
 * <ul>
 * <li>Loading any specific properties</li>
 * <li>Initialing a spring BeanPostProcessor implementation</li>
 * </ul>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/25
 * @since 2015/7/25
 */
@Configuration
@EnableAsync
@EnableScheduling
@Order(1)
@ComponentScan(basePackageClasses = Application.class,
        excludeFilters = @ComponentScan.Filter({Controller.class, Configuration.class}))
public class AppConfig {

    /**
     * A bean that loading any properties files as needed
     *
     * @return a PropertyPlaceholderConfigurer object
     */
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ClassPathResource[] resources = new ClassPathResource[]
                {new ClassPathResource("app.properties"), new ClassPathResource("jdbc.properties"),
                        new ClassPathResource("hibernate.properties"),
                        new ClassPathResource("hikari.properties"), new ClassPathResource("mail.properties"),
                        new ClassPathResource("freemarker.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    /**
     * A bean that implement spring BeanPostProcessor for logging any method annotated with @Log
     *
     * @return a LoggingBeanPostProcessor object
     */
    @Bean
    static LoggingBeanPostProcessor activitiConfigurationPostProcessor() {
        return new LoggingBeanPostProcessor();
    }

}
