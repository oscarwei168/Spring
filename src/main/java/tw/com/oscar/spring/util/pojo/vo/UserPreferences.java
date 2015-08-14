/**
 * UserPreferences.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/13
 * <p>
 * H i s t o r y
 * 2015/8/13 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.pojo.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Title: UserPreferences.java<br>
 * </p>
 * <strong>Description:</strong> A user preferences bean<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/13
 * @since 2015/8/13
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserPreferences {
}
