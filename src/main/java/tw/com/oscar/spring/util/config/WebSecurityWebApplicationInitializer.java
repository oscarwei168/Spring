/**
 * WebSecurityWebApplicationInitializer.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/14
 * <p>
 * H i s t o r y
 * 2015/8/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * <p>
 * Title: WebSecurityWebApplicationInitializer.java<br>
 * </p>
 * <strong>Description:</strong> A spring security framework 4 initializer used for registering
 * springSecurityFilterChain<br>
 * This function include: - <br>
 * <ul><li>Registering spring security springSecurityFilterChain</li></ul>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/14
 * @since 2015/8/14
 */
@Order(2)
public class WebSecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
