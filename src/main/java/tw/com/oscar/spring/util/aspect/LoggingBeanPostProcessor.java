/**
 * LoggingBeanPostProcessor.java
 * Title: Oscar Wei Web Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: LoggingBeanPostProcessor
 *
 * @author Oscar Wei
 * @since 2015/3/13
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/13 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import tw.com.oscar.spring.util.annotation.Log;

/**
 * <p>
 * Title: LoggingBeanPostProcessor.java
 * </p>
 * <strong>Description:</strong> // TODO<br>
 * This function include: - <br>
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
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingBeanPostProcessor.class);

    /**
     * A method invoked before bean has been initialized
     *
     * @param bean     a bean object
     * @param beanName a bean name
     * @return a bean object
     * @throws BeansException throw exception when:<br>
     *                        <ul><li>if any exception occurred</li></ul>
     */
    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        LOGGER.info("Bean name : {}", beanName);
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            Logger logger = LoggerFactory.getLogger(bean.getClass());
            ReflectionUtils.makeAccessible(field);
            field.set(bean, logger);
        }, field1 -> field1.isAnnotationPresent(Log.class));

        ReflectionUtils.doWithMethods(bean.getClass(), method -> {
            ReflectionUtils.makeAccessible(method);
            LOGGER.info("Detected @Log on : {}, method : {}", bean.getClass().getName(), method.getName());
        }, method1 -> method1.isAnnotationPresent(Log.class));
        return bean;
    }

    /**
     * A method invoked after bean has been initialized
     *
     * @param bean     a bean object
     * @param beanName a bean name
     * @return a bean object
     * @throws BeansException throw exception when:<br>
     *                        <ul><li>if any exception occurred</li></ul>
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
