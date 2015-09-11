/**
 * HelloController.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/7
 * <p>
 * H i s t o r y
 * 2015/8/7 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * Title: HelloController.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/7
 * @since 2015/8/7
 */
@Controller("/secure")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        LOGGER.info("{}", "[Enter] HelloController.login");
//        Authentication request = new UsernamePasswordAuthenticationToken("", "");
//        Authentication result = this.authenticationManager.authenticate(request);
//        SecurityContextHolder.getContext().setAuthentication(request);
        return "secure/hello";
    }
}
