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
import com.jamonapi.*;
import com.jamonapi.aop.spring.JamonAopKeyHelper;
import com.jamonapi.aop.spring.JamonAopKeyHelperInt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
    private static final String KEYWORD_RETURN = "\n";
    // private static final String MONITOR = "PERFORMANCE_MONITOR";
    private static final String EXCEPTION = "Exception";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DecimalFormat MS_FORMAT = new DecimalFormat("###,###");
    private static final DecimalFormat AVG_MS_FORMAT = new DecimalFormat("###,###.###");
    protected Monitor monitor;
    protected JamonAopKeyHelperInt keyHelper;

    @Value("${app.loggingAspect.enabled}")
    private boolean enabled;

    /**
     * A default constructor
     */
    public LoggingAspect() {
        keyHelper = new JamonAopKeyHelper();
        setExceptionBufferListener(true);
        setUseArgsWithMethodDetails(true);
        setUseArgsWithExceptionDetails(true);
    }

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
            Object retVal = null;
            String label = keyHelper.getLabel(joinPoint);
            String details = keyHelper.getDetails(joinPoint);
            LOGGER.info("Label: {}, Details: {}", label, details);
            MonKeyImp key = new MonKeyImp(label, details, "ms.");
            this.monitor = MonitorFactory.start(key);
            // StopWatch stopWatch = new StopWatch();
            // stopWatch.start();
            LOGGER.info("Calling " + joinPoint.getSignature().getName() + " method...");
            try {
                retVal = joinPoint.proceed();
                StringBuffer buffer = new StringBuffer(KEYWORD_RETURN);
                buffer.append("------------------------------------------").append(KEYWORD_RETURN);
                buffer.append("Target: ");
                buffer.append(joinPoint.getTarget().getClass().getName()).append(".");
                buffer.append(joinPoint.getSignature().getName()).append("(");
                Object[] args = joinPoint.getArgs();
                buffer.append(Joiner.on(", ").join(args));
                buffer.append(")").append(KEYWORD_RETURN);
                // buffer.append(" execution time : ").append(stopWatch.getTime()).append("ms").append(KEYWORD_RETURN);
                buffer.append("Execution Date: ").append(this.getLastAccess()).append(KEYWORD_RETURN);
                buffer.append("Service Call: ").append(this.getHits()).append(KEYWORD_RETURN);
                buffer.append("Last Execution Time: ").append(this.getLastValue()).append(" ms").append(KEYWORD_RETURN);
                buffer.append("Avg Execution Time: ").append(this.getAvg()).append(" ms").append(KEYWORD_RETURN);
                buffer.append("Total Execution Time: ").append(this.getTotal()).append(" ms").append(KEYWORD_RETURN);
                buffer.append("Min Execution Time: ").append(this.getMin()).append(" ms").append(KEYWORD_RETURN);
                buffer.append("Max Execution Time: ").append(this.getMax()).append(" ms").append(KEYWORD_RETURN);
                buffer.append("------------------------------------------");
                LOGGER.info(buffer.toString());
            } catch (Throwable throwable) {
                LOGGER.error(throwable.getMessage(), throwable);
                String exceptionDetails = keyHelper.getDetails(joinPoint, throwable);
                key.setDetails(exceptionDetails);
                trackException(throwable, exceptionDetails);
                throw throwable;
            } finally {
                // stopWatch.stop();
                this.monitor.stop();
            }

            return retVal;
        } else {
            return joinPoint.proceed();
        }
    }

    public void setExceptionBufferListener(boolean enabled) {
        MonKey key = new MonKeyImp(MonitorFactory.EXCEPTIONS_LABEL, EXCEPTION);
        boolean hasBufferListener = MonitorFactory.getMonitor(key).hasListener("value", "FIFOBuffer");

        if (enabled && !hasBufferListener) {
            MonitorFactory.getMonitor(key).addListener("value", JAMonListenerFactory.get("FIFOBuffer"));
        } else if (!enabled && hasBufferListener) {
            MonitorFactory.getMonitor(key).removeListener("value", "FIFOBuffer");

        }
    }

    /**
     * Specifies to have the methods arguments viewable in the jamon monitor details. This is viewable from the jamon
     * web application.  It will allow you to see what values were passed to monitored methods via the web app.
     * By default this is disabled.
     *
     * @param useArgsWithMethodDetails is use
     */
    public void setUseArgsWithMethodDetails(boolean useArgsWithMethodDetails) {
        keyHelper.setUseArgsWithMethodDetails(useArgsWithMethodDetails);
    }

    /**
     * Specifies to have the methods arguments viewable in the jamon monitor details. This is viewable from the jamon
     * web application.  It will allow you to see what values were passed to monitored methods that threw an exception.
     * By default this is disabled.
     *
     * @param useArgsWithExceptionDetails is use
     */
    public void setUseArgsWithExceptionDetails(boolean useArgsWithExceptionDetails) {
        keyHelper.setUseArgsWithExceptionDetails(useArgsWithExceptionDetails);
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

    /**
     * Add monitors for the thrown exception and also put the stack trace in the details portion of the key
     *
     * @param exception        a Throwable object
     * @param exceptionDetails a exception detail
     */
    private void trackException(Throwable exception, String exceptionDetails) {
        MonitorFactory.add(new MonKeyImp(keyHelper.getExceptionLabel(exception), exceptionDetails, EXCEPTION), 1);
        MonitorFactory.add(new MonKeyImp(MonitorFactory.EXCEPTIONS_LABEL, exceptionDetails, EXCEPTION), 1);
    }

    /**
     * A method used to obtaining last access timestamp
     *
     * @return a formatted timestamp
     */
    private String getLastAccess() {
        return DATE_FORMAT.format(this.monitor.getLastAccess());
    }

    /**
     * A method used to obtaining invoking counts
     *
     * @return a counter that has been invoked
     */
    private String getHits() {
        return MS_FORMAT.format(this.monitor.getHits());
    }

    /**
     * A method used for obtaining last access time
     *
     * @return the last access time
     */
    private String getLastValue() {
        return MS_FORMAT.format(this.monitor.getLastValue());
    }

    /**
     * A method used for obtaining avg access time
     *
     * @return the avg access time
     */
    private String getAvg() {
        return AVG_MS_FORMAT.format(this.monitor.getAvg());
    }

    /**
     * A method used for obtaining total access time
     *
     * @return the total access time
     */
    private String getTotal() {
        return MS_FORMAT.format(this.monitor.getTotal());
    }

    /**
     * A method used for obtaining minimum access time
     *
     * @return the minimum access time
     */
    private String getMin() {
        return MS_FORMAT.format(this.monitor.getMin());
    }

    /**
     * A method used for obtaining maximum access time
     *
     * @return the maximum access time
     */
    private String getMax() {
        return MS_FORMAT.format(this.monitor.getMax());
    }
}
