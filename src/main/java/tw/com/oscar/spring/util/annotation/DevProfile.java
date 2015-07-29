/**
 * DevProfile.java
 * Title: Oscar Wei Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/27
 * <p>
 * H i s t o r y
 * 2015/7/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.annotation;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Title: DevProfile.java
 * </p>
 * <strong>Description:</strong> A custom annotation for development profile<br>
 * This function include: - <br>
 * <ul><li>A marker interface</li></ul>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/27
 * @since 2015/7/27
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Profile("dev")
public @interface DevProfile {
}
