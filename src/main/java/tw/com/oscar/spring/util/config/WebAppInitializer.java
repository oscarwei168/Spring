/**
 * WebAppInitializer.java
 * Title: Oscar Wei Web Project
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

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import tw.com.oscar.spring.util.annotation.Log;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * <p>
 * Title: WebAppInitializer.java
 * </p>
 * <strong>Description:</strong> A component that initialize web application<br>
 * This function include: - <br>
 * <ul><li>Setting spring mvc servlet mapping</li></ul>
 * <ul><li>Setting up servlet-specific initial parameters</li></ul>
 * <ul><li>Setting and loading all spring configurations as needed</li></ul>
 * <ul><li>Setting OpenSessionInView pattern filter up</li></ul>
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
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

    /**
     * A entry method when application start up
     *
     * @param servletContext a ServletContext object
     * @throws ServletException throw exception when:<br>
     *                          <ul><li>if any exception occurred</li></ul>
     */
    @Override
    @Log
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.info("[Enter] WebAppInitializer.onStartup");
        super.onStartup(servletContext);
        servletContext.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        // servletContext.addListener(null);
    }

    /**
     * A method for setting spring mvc servlet url mapping up
     *
     * @return a array that contains servlet mapping
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /**
     * A method for loading all spring configuration setting as needed
     *
     * @return a array that contains all spring configuration
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // SecurityConfig.class, JpaConfig.class
        return new Class<?>[] {AppConfig.class, HibernateConfig.class, CacheConfig.class, MailConfig
                .class, FreemarkerConfig.class};
    }

    /**
     * A method for loading web servlet-specific configuration setting
     *
     * @return a array that contains servlet-specific configuration
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebAppConfig.class};
    }

    /**
     * A method for initialize all filters as needed
     *
     * @return a array of Filter objects
     */
    @Override
    protected Filter[] getServletFilters() {

        // Open session in view pattern
        OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();

        // Encoding filter
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(CharEncoding.UTF_8);
        characterEncodingFilter.setForceEncoding(true);

        // TODO Spring security filter
        // DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");

        return new Filter[] {openSessionInViewFilter, characterEncodingFilter};
    }

    /**
     * A method for setting servlet-specific initialize parameters
     *
     * @param registration a ServletRegistration.Dynamic object
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("defaultHtmlEscape", "true");
        registration.setInitParameter("webAppRootKey", "oscarwei168.root");
        registration.setInitParameter("productionMode", "false");
        registration.setInitParameter("closeIdleSessions", "true");
        registration.setInitParameter("pushmode", "automatic");
        registration.setAsyncSupported(true);
    }
}
