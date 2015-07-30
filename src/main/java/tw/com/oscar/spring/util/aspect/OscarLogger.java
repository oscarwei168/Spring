/**
 * OscarLogger.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/30
 * <p>
 * H i s t o r y
 * 2015/7/30 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: OscarLogger.java<br>
 * </p>
 * <strong>Description:</strong> A aspect for logging <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/30
 * @since 2015/7/30
 */
@Aspect
public class OscarLogger {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OscarLogger.class);

    /**
     * A before logging interceptor
     *
     * @param point a JoinPoint object
     */
    @Before("execution(* tw.com.oscar.spring.function.*(..))")
    public void log(JoinPoint point) {
        LOGGER.info("Calling " + point.getSignature().getName() + " method...");
    }
}
