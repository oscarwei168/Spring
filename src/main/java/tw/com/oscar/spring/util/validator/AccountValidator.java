/**
 * AccountValidator.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/6
 * <p>
 * H i s t o r y
 * 2015/8/6 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tw.com.oscar.spring.domain.Account;

/**
 * <p>
 * Title: AccountValidator.java<br>
 * </p>
 * <strong>Description:</strong> A Account entity validator<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/6
 * @since 2015/8/6
 */
@Component
public class AccountValidator implements Validator {

    /**
     * A method for check whether can validate instance of supplied class or not?
     *
     * @param clazz a Class object
     * @return true if target is Account entity, or false otherwise
     */
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("Class:" + clazz.getName());
        return Account.class.equals(clazz);
    }

    /**
     * A method for validating Account entity properties
     *
     * @param target a target object
     * @param errors a Errors object
     */
    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "account.filed.empty", new String[] {"Account",
                "Username"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "account.filed.empty", new String[]
                {"Account", "First Name"});

        if (null == account.getSalary()) {
            errors.rejectValue("salary", "account.filed.empty", new String[] {"Account", "Salary"}, "The salary " +
                    "cannot be empty");
        }
    }
}
