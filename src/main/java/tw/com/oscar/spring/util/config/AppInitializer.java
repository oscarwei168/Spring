/**
 * AppInitializer.java
 * Title: Oscar Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/24
 * <p>
 * H i s t o r y
 * 2015/7/24 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * Title: AppInitializer.java
 * </p>
 * <strong>Description:</strong> A initialize/destroy bean sample<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/24
 * @since 2015/7/24
 */
@Component
public class AppInitializer implements InitializingBean, DisposableBean {

    private static Logger LOGGER = LoggerFactory.getLogger(AppInitializer.class);

    /**
     * A method for initialize some setting when bean started
     *
     * @throws Exception throw exception when:<br>If any exception occurred
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Oscar Wei web project initializer : {}", new Date());
    }

    /**
     * A method for clean up something when bean destroyed
     *
     * @throws Exception throw exception when:<br>
     *                   <ul><li>if any exception occurred</li></ul>
     */
    @Override
    public void destroy() throws Exception {
        LOGGER.info("Oscar Wei web project destroy : {}", new Date());
    }

    /**
     * A method is running in fixed-rate schedule
     */
    // @Scheduled(fixedDelay = 5 * 60 * 1000, initialDelay = 10 * 1000) // TODO fixedRate = 60 * 1000 * 5
    public void scheduleJob() {
        LOGGER.info("You need to work harder : {}", new Date());
    }

    /**
     * A method is running in cron schedule
     */
    // @Scheduled(cron = "* */5 * * * MON-FRI")
    public void scheduleCron() {
        LOGGER.info("Schedule cron : {}", new Date());
    }
}
