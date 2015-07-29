/**
 * CSRFHandlerInterceptor.java
 * Title: DTS Project
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        LOGGER.info("[Enter] CSRFHandlerInterceptor.preHandle()");
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return true;
        } else {
            String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
            String requestToken = CSRFTokenManager.getTokenFromRequest(request);
            LOGGER.info("Session token : {}", sessionToken);
            LOGGER.info("Request token : {}", requestToken);
            if (sessionToken.equals(requestToken)) {
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
                return true; // TODO need to false
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
        LOGGER.info("[End] CSRFHandlerInterceptor.postHandle()");
    }
}
