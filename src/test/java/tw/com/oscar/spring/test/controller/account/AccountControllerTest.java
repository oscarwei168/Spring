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
package tw.com.oscar.spring.test.controller.account;

import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = AppConfig.class)
// @WebAppConfiguration
// @ActiveProfiles({"dev"})
public class AccountControllerTest {

    // @Autowired
    // private CacheManager cacheManager;

    @Mock
    private MessageSource messageSource;
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

    @Test(expectedExceptions = Exception.class)
    public void testSomething() throws Exception {
        throw new Exception("Respected");
    }

    @Test(timeOut = 5000L)
    public void testSomething1() throws Exception {
        Thread.sleep(3000L);
    }

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
