/**
 * SecurityController.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/9/19
 * <p>
 * H i s t o r y
 * 2015/9/19 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title: SecurityController.java<br>
 * </p>
 * <strong>Description:</strong> A security-specific controller handler<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/9/19
 * @since 2015/9/19
 */
@Controller
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    /**
     * A method used for handle logout process
     *
     * @param request  a HttpServletRequest object
     * @param response a HttpServletResponse object
     * @return the uri when logout
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("{}", "WebAppConfig.logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            removeRememberMeCookie(request, response, authentication);
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/index";
    }

    /**
     * A security access denied action handler
     *
     * @param model a ModelMap object
     * @return the url when access denied
     */
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        model.addAttribute("message", "");
        return "error/accessDenied";
    }

    /**
     * A method used for removing spring security 'RememberMe' cookies and persisted token
     *
     * @param request        a HttpServletRequest object
     * @param response       a HttpServletResponse object
     * @param authentication a Authentication object
     */
    private void removeRememberMeCookie(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Cookie cookie = new Cookie(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
        cookie.setMaxAge(0);
        cookie.setPath(getCookiePath(request));
        response.addCookie(cookie);

        /** Deleting spring security 'remember me' token **/
        persistentTokenRepository.removeUserTokens(authentication.getName());
    }

    /**
     * A method used for obtaining cookies path
     *
     * @param request a HttpServletRequest object
     * @return the cookies path
     */
    private String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
}
