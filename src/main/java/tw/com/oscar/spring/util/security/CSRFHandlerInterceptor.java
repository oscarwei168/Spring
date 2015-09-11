/**
 * CSRFHandlerInterceptor.java
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title: CSRFHandlerInterceptor.java
 * </p>
 * <strong>Description:</strong> A interceptor for handle CSRF attach <br>
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
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSRFHandlerInterceptor.class);
    private static final String METHOD_POST = "POST";

    /**
     * A method for validating session/request token before processing request
     *
     * @param request  a HttpServletRequest object
     * @param response a HttpServletResponse object
     * @param handler  a object
     * @return true if validate success, or false otherwise
     * @throws Exception throw exception when:<br>
     *                   <ul><li>If any exception occurred</li></ul>
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!request.getMethod().equalsIgnoreCase(METHOD_POST)) {
            return true;
        } else {
            String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
            String requestToken = CSRFTokenManager.getTokenFromRequest(request);
            LOGGER.info("CSRF session token : {}", sessionToken);
            LOGGER.info("CSRF request token : {}", requestToken);
            if (sessionToken.equals(requestToken)) {
                LOGGER.info("{}", "CSRF token validation successfully...");
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
                return false;
            }
        }
    }

    /**
     * A method for handle something after response body
     *
     * @param request      a HttpServletRequest object
     * @param response     a HttpServletResponse object
     * @param handler      a object
     * @param modelAndView a ModelAndView object
     * @throws Exception throw exception when:<br>
     *                   <ul><li>If any exception occurred</li></ul>
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
