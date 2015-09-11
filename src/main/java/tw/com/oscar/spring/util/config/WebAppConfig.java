/**
 * WebAppConfig.java
 * Title: Oscar Wei Project
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

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.cache.StandardCacheManager;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring4.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring4.web.view.ThymeleafTilesView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import tw.com.oscar.spring.Application;
import tw.com.oscar.spring.util.formatter.AccountFormatter;
import tw.com.oscar.spring.util.formatter.DateFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * Title: WebAppConfig.java
 * </p>
 * <strong>Description:</strong> A spring component that initial spring mvc features <br>
 * This function include: - <br>
 * <ul><li>Setting locale interceptor</li></ul>
 * <ul><li>Setting i18n message source</li></ul>
 * <ul><li>Setting locale resolver</li></ul>
 * <ul><li>Setting Thymeleaf UI template engine</li></ul>
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
@ComponentScan(basePackageClasses = {Application.class}, includeFilters = @ComponentScan.Filter
        (Controller.class), useDefaultFilters = false)
class WebAppConfig extends WebMvcConfigurationSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppConfig.class);

    private static final int CACHE_PERIOD = 31556926; // one year

    private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";

    private static final String VIEW = "/WEB-INF/views/";

    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    /**
     * A bean for setting spring mvc locale resolver which used cookie storing in client
     *
     * @return a LocaleResolver object
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
        return cookieLocaleResolver;
    }

    /**
     * A bean for specify i18n message source properties
     *
     * @return a MessageSource object
     */
    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding(CharEncoding.UTF_8);
        // if true, the key of the message will be displayed if the key is not found,
        // instead of throwing a NoSuchMessageException
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    /**
     * Setting Thymeleaf template engine up
     *
     * @return The TemplateResolver implementation
     */
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix(VIEW);
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5"); // XHTML is default
        resolver.setOrder(1); // for multiple template resolver priority

        resolver.setCacheable(true); // default is true
        // if not set, entries would be cached until expelled by LRU
        resolver.setCacheTTLMs(1800000L); // 0.5 hour
        // resolver.getCacheablePatternSpec().addPattern("/users/*");
        // resolver.getXmlTemplateModePatternSpec().addPattern("*.xhtml");
        // resolver.setCharacterEncoding(CharEncoding.UTF_8);
        // resolver.addTemplateAlias("adminHome", "profiles/admin/home");
        // resolver.setTemplateAliases(new HashMap<>());
        return resolver;
    }

    /**
     * A bean for setting spring template engine
     *
     * @return a SpringTemplateEngine object
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        StandardCacheManager cacheManager = new StandardCacheManager();
        cacheManager.setTemplateCacheMaxSize(100); // default is 50
        engine.setCacheManager(cacheManager);
        engine.addTemplateResolver(templateResolver());
        engine.addDialect(new SpringSecurityDialect());
        engine.addDialect(new TilesDialect());
        engine.addDialect(new LayoutDialect());
        return engine;
    }

    /**
     * A bean that setting Thymeleaf as view resolver engine
     *
     * @return a ViewResolver object
     */
    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding(CharEncoding.UTF_8);
        viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        // all message/* views will not be handled by this resolver;
        viewResolver.setExcludedViewNames(new String[] {"message/*"});
        return viewResolver;
    }

    /**
     * A bean for setting apache tiles as view resolver
     *
     * @return a ViewResolver object
     */
    @Bean
    public ViewResolver tilesViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setViewClass(ThymeleafTilesView.class);
        viewResolver.setCharacterEncoding(CharEncoding.UTF_8);
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE);
        return viewResolver;
    }

    /**
     * A bean for loading thymeleaf-tiles configuration
     *
     * @return a ThymeleafTilesConfigurer object
     */
    @Bean
    public ThymeleafTilesConfigurer tilesConfigurer() {
        ThymeleafTilesConfigurer ttc = new ThymeleafTilesConfigurer();
        ttc.setDefinitions("/WEB-INF/config/tiles-definitions.xml");
        // ttc.setDefinitions(new String[] {"classpath:tiles/tiles-def.xml"});
        return ttc;
    }

    /**
     * A bean for setting spring mvc simple exception handler
     *
     * @return a SimpleMappingExceptionResolver object
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
        mappings.put("org.springframework.dao.DataAccessException", "dataAccessFailure");
        mappings.put("org.springframework.transaction.TransactionException", "dataAccessFailure");
        b.setExceptionMappings(mappings);
        return b;
    }

    /**
     * Enable security features like protection against CSRF
     *
     * @return The RequestDataValueProcess implementation
     * @deprecated it is deprecated because spring framework 4 include CSRF token functionality automatically
     */
//    @Bean
//    public RequestDataValueProcessor requestDataValueProcessor() {
//        return new CSRFRequestDataValueProcessor();
//    }

    /**
     * A bean for obtaining Jaxb2RootElementHttpMessageConverter object used to JAXB marshal
     *
     * @return a Jaxb2RootElementHttpMessageConverter object
     */
    @Bean
    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
        return new Jaxb2RootElementHttpMessageConverter();
    }

    /**
     * A spring HttpMessageConverter for handling media type like json, etc.
     *
     * @return a HttpMessageConverter object
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
        return converter;
    }

    /**
     * A method used for registering /login to maping to view name 'login'
     *
     * @param registry a ViewControllerRegistry object
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("pages/login");
        registry.addViewController("/secure/hello").setViewName("secure/hello");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * A method used for configure message converter when RESTful controller response
     *
     * @param converters list of HttpMessageConverter objects
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(jaxb2RootElementHttpMessageConverter());
        // converters.add(responseBodyConverter());
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
        requestMappingHandlerMapping.setRemoveSemicolonContent(false);
        return requestMappingHandlerMapping;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * A method for handle resources location
     *
     * @param registry a ResourceHandlerRegistry object
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION)
                .setCachePeriod(CACHE_PERIOD);
    }

    /**
     * Enable forwarding to the “default” Servlet. The “default” Servlet is used to handle static content such as
     * CSS, HTML and images
     *
     * @param configurer a DefaultServletHandlerConfigurer object
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * A method for adding some interceptors
     *
     * @param registry a InterceptorRegistry object
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // TODO registry.addInterceptor(new CSRFHandlerInterceptor());

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * A method for register spring formatter
     *
     * @param registry a FormatterRegistry object
     */
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new AccountFormatter());
    }


    /**
     * A controller for favorite icon mapping
     */
    @Controller
    static class FaviconController {

        /**
         * Handles favicon.ico requests assuring no <code>404 Not Found</code> error is returned
         *
         * @return a favorite icon uri mapping
         */
        @RequestMapping("favicon.ico")
        String favicon() {
            return "forward:/resources/images/favicon.ico";
        }
    }

    /**
     * A controller for '/' and '/index' mapping
     */
    @Controller
    static class IndexController {

        /**
         * A method for mapping '/' or '/index' url
         *
         * @param principal      a Principle object
         * @param authentication a Authentication object
         * @return a template uri
         */
        @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
        String index(Principal principal, Authentication authentication) {
            // , @AuthenticationPrincipal User currentUser
            LOGGER.info("Principle exist : " + (null != principal));
            LOGGER.info("Authentication exist : " + (null != authentication));
            // User user = (User) authentication.getPrincipal();
            // LOGGER.info("Username : {}", null == currentUser ? "" : currentUser.getUsername());
            return "index";
        }
    }

    @Controller
    static class LogoutController {

        /**
         * A method used for handle logout process
         *
         * @param request        a HttpServletRequest object
         * @param response       a HttpServletResponse object
         * @param authentication a Authentication object
         * @return the uri when logout
         */
        @RequestMapping(value = "/logout", method = RequestMethod.GET)
        String logout(HttpServletRequest request, HttpServletResponse response) {
            LOGGER.info("{}", "WebAppConfig.logout");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (null != authentication) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            return "redirect:/index";
        }
    }
}
