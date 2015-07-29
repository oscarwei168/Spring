/**
 * AccountFormatter.java
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
package tw.com.oscar.spring.util.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import tw.com.oscar.spring.domain.Account;
import tw.com.oscar.spring.function.testcase.service.AccountService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * <p>
 * Title: AccountFormatter.java<br>
 * </p>
 * <strong>Description:</strong> A spring formatter for Account entity<br>
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
public class AccountFormatter implements Formatter<Account> {

    @Autowired
    private AccountService accountService;

    /**
     * A default constructor for AccountFormatter
     */
    public AccountFormatter() {
    }

    /**
     * A method for parse argument then return specific Account object
     *
     * @param text   a text
     * @param locale a Locale object
     * @return a Account object
     * @throws ParseException throw exception when:<br>
     *                        <ul><li>exception occurred when parse text value</li></ul>
     */
    @Override
    public Account parse(String text, Locale locale) throws ParseException {
        Objects.requireNonNull(text);
        long id = Long.parseLong(text);
        return accountService.findByPid(id).get();
    }

    /**
     * A method for printing specific string
     *
     * @param object a Account object
     * @param locale a Locale object
     * @return a string that wishing to printing
     */
    @Override
    public String print(Account object, Locale locale) {
        return (null != object ? object.getPid().toString() : "");
    }
}
