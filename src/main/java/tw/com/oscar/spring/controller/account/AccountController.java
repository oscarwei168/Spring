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
package tw.com.oscar.spring.controller.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.service.account.AccountService;
import tw.com.oscar.spring.vo.Quote;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

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
@SessionAttributes("accounts")
@Secured("ROLE_USER")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountService accountService;

//    @Autowired
//    private AccountValidator accountValidator;

    /**
     * Export common account listing
     *
     * @return list of account objects
     */
    @ModelAttribute("accounts")
    public List<Account> accounts() {
        LOGGER.info("[Enter] AccountController.accounts");
        return accountService.findAll().collect(toList());
    }

    /**
     * Export a account object to temporary map
     *
     * @return a Account object
     */
    @ModelAttribute("account")
    public Account account() {
        LOGGER.info("[Enter] AccountController.account");
        return new Account();
    }

    /**
     * Binding request parameters to method parameters with @PathValue annotation sample
     *
     * @param id     a request parameter named id
     * @param model  a Model object
     * @param locale a Locale object
     * @return a Thymeleaf template uri
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getAccount(@PathVariable Long id, Model model, Locale locale) {
        String msg = messageSource.getMessage("app.title", null, locale);
        LOGGER.info("Account message : {}", msg);
        model.addAttribute("check", "value");
        model.addAttribute("account", accountService.findById(id));
        return "index";
    }

    /**
     * Binding request parameters to method parameters with @RequestParam annotation sample
     *
     * @param id    a request parameter named id
     * @param model a ModelAndView object
     * @return a ModelAndView object
     */
    @RequestMapping
    public ModelAndView getAccount(
            @RequestParam(value = "id", required = false, defaultValue = "0") Long id,
            ModelAndView model) {
        // ModelAndView model = new ModelAndView("index");
        // ModelAndView model = new ModelAndView("index", "account", accountService.findById(id));
        model.setViewName("index");
        model.addObject("account", accountService.findById(id));
        return model;
    }

    /**
     * A method for handle user submit
     *
     * @param account            a Account object
     * @param result             a BindingResult object
     * @param redirectAttributes a RedirectAttributes object
     * @return a uri
     */
    @RequestMapping(method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("account") @Valid Account account, BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "index";
        }

        redirectAttributes.addFlashAttribute("message", "message here...");
        return "redirect:/index";
    }

    /**
     * A handler used for consuming a RESTful web service
     */
    @RequestMapping("/consume")
    public String consumeAccount() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        LOGGER.info("" + quote);
        return "index";
    }
}
