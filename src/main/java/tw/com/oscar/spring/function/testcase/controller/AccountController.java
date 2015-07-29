/**
 * AccountController.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/27
 * <p>
 * H i s t o r y
 * 2015/7/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.function.testcase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.com.oscar.spring.function.testcase.service.AccountService;
import tw.com.oscar.spring.util.annotation.Log;

import java.util.Locale;

/**
 * <p>
 * Title: AccountController.java<br>
 * </p>
 * <strong>Description:</strong> A controller for Account domain<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/27
 * @since 2015/7/27
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    // @Log
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @Log
    public String getAccount(@PathVariable String pid, Model model, Locale locale) {
        String msg = messageSource.getMessage("app.title", null, locale);
        LOGGER.info("Account message : {}", msg);
        model.addAttribute("check", "value");
        model.addAttribute("accounts", accountService.findAll());
        return "index";
    }
}
