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
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * <p>
 * Title: LoggingAspect.java<br>
 * </p>
 * <strong>Description:</strong> A around logging aspect<br>
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
@Component
@Aspect
public class LoggingAspect {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * A arounr logging interceptor
     *
     * @param joinPoint a JoinPoint object
     * @return the original respect value
     * @throws Throwable throw exception when:<br>
     *                   <ul><li>if any exception occurred</li></ul>
     */
    @Around(value = "execution(* tw.com.oscar.spring.service..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.info("Calling " + joinPoint.getSignature().getName() + " method...");
        Object retVal = joinPoint.proceed();
        stopWatch.stop();

        StringBuffer buffer = new StringBuffer();
        buffer.append(joinPoint.getTarget().getClass().getName()).append(".");
        buffer.append(joinPoint.getSignature().getName()).append("(");
        Object[] args = joinPoint.getArgs();
        // for (Object arg : args) {
            buffer.append(Joiner.on(", ").join(args));
        //}
//        if (args.length > 0) {
//            buffer.deleteCharAt(buffer.length() - 2);
//        }
        buffer.append(")");
        buffer.append(" execution time : ").append(stopWatch.getTime()).append("ms");
        LOGGER.info(buffer.toString());
        return retVal;
    }
}
