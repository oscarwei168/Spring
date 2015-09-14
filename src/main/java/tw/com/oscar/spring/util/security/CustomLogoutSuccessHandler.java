/**
 * CustomLogoutSuccessHandler.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/9/11
 * <p>
 * H i s t o r y
 * 2015/9/11 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Title: CustomLogoutSuccessHandler.java<br>
 * </p>
 * <strong>Description:</strong> A customer spring security logout success handler<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/9/11
 * @since 2015/9/11
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * @param request        a HttpServletRequest object
     * @param response       a HttpServletResponse object
     * @param authentication a Authentication object
     * @throws IOException      throw exception when:<br>
     * @throws ServletException throw exception when:<br>
     * @see LogoutSuccessHandler#onLogoutSuccess(HttpServletRequest, HttpServletResponse, Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String refererUrl = request.getHeader("Referer");
        // auditService.track("Logout from: " + refererUrl);
        System.out.println("Logout success");

        super.onLogoutSuccess(request, response, authentication);
    }
}
