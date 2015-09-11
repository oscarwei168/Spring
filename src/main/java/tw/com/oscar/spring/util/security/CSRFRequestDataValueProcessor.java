/**
 * CSRFRequestDataValueProcessor.java
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

import org.springframework.web.servlet.support.RequestDataValueProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: CSRFRequestDataValueProcessor.java
 * </p>
 * <strong>Description:</strong> //TODO <br>
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
public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor {

    @Override
    public String processAction(HttpServletRequest request, String action, String httpMethod) {
        return action;
    }

    @Override
    public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
        return value;
    }

    @Override
    public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
        Map<String, String> hiddenFields = new HashMap<>();
        hiddenFields.put(CSRFTokenManager.CSRF_PARAM_NAME,
                CSRFTokenManager.getTokenForSession(request.getSession()));
        return hiddenFields;
    }

    @Override
    public String processUrl(HttpServletRequest request, String url) {
        return url;
    }
}
