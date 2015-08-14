/**
 * AccountRestController.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/2
 * <p>
 * H i s t o r y
 * 2015/8/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.controller.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.service.account.AccountService;
import tw.com.oscar.spring.util.pojo.xml.AccountListXML;
import tw.com.oscar.spring.util.pojo.xml.AccountXML;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Title: AccountRestController.java<br>
 * </p>
 * <strong>Description:</strong> A restful controller for account object<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/2
 * @since 2015/8/2
 */
@RestController
@RequestMapping("/accountrest")
public class AccountRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);
    private static AtomicInteger count = new AtomicInteger();

    @Autowired
    private AccountService accountService;

    /**
     * A restful controller sample
     *
     * @param id a request parameter named id
     * @return a Account object
     */
    @RequestMapping(value = "/{id}")
    public Account getAccount(@PathVariable Long id) {
        LOGGER.info("Account id : {}", id);
        count.getAndIncrement();
        return accountService.findById(id).orElse(new Account());
    }

    @RequestMapping(value = "/accountsxml")
    public AccountListXML accountsxml() {
        LOGGER.info("{}", "[Enter] AccountController.accountsxml");
        AccountListXML accountList = new AccountListXML();
        AccountXML a1 = new AccountXML(1L, "oscarwei168", "Oscar", "Wei", "oscarwei168@msn.com");
        AccountXML a2 = new AccountXML(2L, "oscarwei168", "Oscar", "Wei", "oscarwei168@msn.com");
        AccountXML a3 = new AccountXML(3L, "oscarwei168", "Oscar", "Wei", "oscarwei168@msn.com");
        accountList.getAccounts().add(a1);
        accountList.getAccounts().add(a2);
        accountList.getAccounts().add(a3);
        return accountList;
    }

}
