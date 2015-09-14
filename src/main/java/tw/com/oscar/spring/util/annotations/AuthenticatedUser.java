/**
 * AuthenticatedUser.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/9/14
 * <p>
 * H i s t o r y
 * 2015/9/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.annotations;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * <p>
 * Title: AuthenticatedUser.java<br>
 * </p>
 * <strong>Description:</strong> A customer @AuthenticationPrincipal annotation<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author ${USER_NAME}
 * @version v1, 2015/9/14
 * @since 2015/9/14
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface AuthenticatedUser {
}
