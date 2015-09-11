/**
 * CSRFTokenManager.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/25
 * <p>
 * H i s t o r y
 * 2015/7/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * <p>
 * Title: CSRFTokenManager.java
 * </p>
 * <strong>Description:</strong> A class that managing CSRF token into/from HttpServletRequest/HttpSession object<br>
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
final class CSRFTokenManager {

    static final String CSRF_PARAM_NAME = "_csrf";
    private final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";

    /**
     * A method that will obtaining CSRF token from HttpSession object
     *
     * @param session a HttpSession object
     * @return a CSRF token
     */
    static String getTokenForSession(HttpSession session) {
        String token;
        synchronized (session) {
            token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            if (null == token) {
                token = UUID.randomUUID().toString();
                session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
            }
        }
        return token;
    }

    /**
     * A method that will obtaining CSRF token from HttpServletRequest object
     *
     * @param request a HttpServletRequest object
     * @return a CSRF token
     */
    static String getTokenFromRequest(HttpServletRequest request) {
        return request.getParameter(CSRF_PARAM_NAME);
    }

    /**
     * A private constructor
     */
    private CSRFTokenManager() {
    }
}
