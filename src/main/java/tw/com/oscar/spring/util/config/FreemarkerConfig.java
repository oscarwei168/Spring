/**
 * FreemarkerConfig.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/17
 * <p>
 * H i s t o r y
 * 2015/7/17 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import javax.servlet.ServletContext;
import java.util.Locale;

/**
 * <p>
 * Title: FreemarkerConfig.java
 * </p>
 * <strong>Description:</strong> A Freemarker configuration setting <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/17
 * @since 2015/7/17
 */
@Configuration
public class FreemarkerConfig {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerConfig.class);
    
    @Value("${freemarker.defaultEncoding}")
    private String defaultEncoding;
    @Value("${freemarker.templateFolder}")
    private String templateFolder;
    @Value("${freemarker.dateFormat}")
    private String dateFormat;
    @Value("${freemarker.datetimeFormat}")
    private String datetimeFormat;
    @Value("${freemarker.numberFormat}")
    private String numberFormat;
    @Value("${freemarker.dtsServerUrl}")
    private String dtsServerUrl;
    
    @Autowired
    private ServletContext servletContext;
    
    /**
     * A method for obtain Freemarker configuration object
     *
     * @return the Freemarker configuration object
     * @throws Exception Throw exception when:<br>If any exception occurred
     */
    @Bean(name = "configuration")
    @Description("A Freemarker configuration bean")
    public freemarker.template.Configuration getFreemarkerConfiguration() throws Exception {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setIncompatibleImprovements(new Version(2, 3, 23));
        cfg.setLocale(Locale.getDefault());
        cfg.setDefaultEncoding(defaultEncoding);
        cfg.setDateFormat(dateFormat);
        cfg.setDateTimeFormat(datetimeFormat);
        cfg.setNumberFormat(numberFormat);
        cfg.setServletContextForTemplateLoading(servletContext, templateFolder);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        /** Shared variables definition **/
        cfg.setSharedVariable("dtsServerUrl", dtsServerUrl);
        LOGGER.info("Freemarker template configuration done");
        return cfg;
    }
    
}

