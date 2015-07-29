/**
 * AccountControllerTest.java
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
package tw.com.oscar.spring.test.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.function.testcase.controller.AccountController;
import tw.com.oscar.spring.function.testcase.service.AccountService;
import tw.com.oscar.spring.util.config.AppConfig;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * <p>
 * Title: AccountControllerTest.java
 * </p>
 * <strong>Description:</strong> A unit testing class<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/27
 * @since 2015/7/27
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class)
//@WebAppConfiguration
//@ActiveProfiles({"dev", "mysql"})
public class AccountControllerTest {

    // @Autowired
    // private CacheManager cacheManager;

//    @Mock
//    private MessageSource messageSource;
//
//    @Mock
//    private AccountService accountService;
//
//    @Mock
//    private BindingResult result;
//
//    @InjectMocks
//    private AccountController accountController;

//    @Spy
//    private List<Account> accountList = new ArrayList<>();
//
//    @Spy
//    Model model;
//
//    private MockMvc mockMvc;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
////        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
////        accountList = genAccountList();
//    }

//    @Test
//    public void testSomething() throws Exception {
////        mockMvc.perform(get("/account/{pid}", 1L)).andExpect(status().isOk())
////                .andExpect(view().name("account")).andExpect(model().attribute("check", "value"));
//    }

//    private List<Account> genAccountList() {
//        Account account1 = new Account();
//
//        accountList.add(account1);
//
//        Account account2 = new Account();
//
//        accountList.add(account2);
//        return null;
//    }
}
