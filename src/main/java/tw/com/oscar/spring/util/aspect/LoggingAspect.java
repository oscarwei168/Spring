/**
 * LoggingAspect.java
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

import com.google.common.base.Joiner;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Title: LoggingAspect.java<br>
 * </p>
 * <strong>Description:</strong> A around logging aspect<br>
 * This function include: - A around aspect used for calculating execution time<br>
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
@Component
@Aspect
public class LoggingAspect {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Value("${app.loggingAspect.enabled}")
    private boolean enabled;

    /**
     * A around logging interceptor
     *
     * @param joinPoint a JoinPoint object
     * @return the original respect value
     * @throws Throwable throw exception when:<br>
     *                   <ul><li>if any exception occurred</li></ul>
     */
    @Around(value = "execution(* tw.com.oscar.spring.service..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Logging aspect enabled : {}", enabled);
        if (this.enabled) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            LOGGER.info("Calling " + joinPoint.getSignature().getName() + " method...");
            Object retVal = joinPoint.proceed();
            stopWatch.stop();

            StringBuffer buffer = new StringBuffer();
            buffer.append(joinPoint.getTarget().getClass().getName()).append(".");
            buffer.append(joinPoint.getSignature().getName()).append("(");
            Object[] args = joinPoint.getArgs();
            buffer.append(Joiner.on(", ").join(args));
            buffer.append(")");
            buffer.append(" execution time : ").append(stopWatch.getTime()).append("ms");
            LOGGER.info(buffer.toString());
            return retVal;
        } else {
            return joinPoint.proceed();
        }
    }

    /**
     * A getter for property 'enabled'
     *
     * @return the value of property enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * A setter for property 'enabled'
     *
     * @param enabled a value of property 'enabled'
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
