/**
 * ThymeleafController.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/8
 * <p>
 * H i s t o r y
 * 2015/8/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.controller.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.com.oscar.spring.util.pojo.vo.AccountVO;

/**
 * <p>
 * Title: ThymeleafController.java<br>
 * </p>
 * <strong>Description:</strong> A controller used for Thymeleaf template showcase<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/8
 * @since 2015/8/8
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafController.class);

    /**
     * Preparing a account value object
     *
     * @return a AccountVO object
     */
    @ModelAttribute("accountVO")
    public AccountVO prepareAccountVO() {
        return new AccountVO();
    }

    /**
     * A method for handling Thymeleaf template showcase
     *
     * @return a uri
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showcase() {
        return "pages/thymeleaf";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute AccountVO accountVO) {
        LOGGER.info("{}", accountVO);
        return "redirect:/thymeleaf";
    }
}
