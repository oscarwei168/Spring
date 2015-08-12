/**
 * CentralControllerAdvice.java
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
package tw.com.oscar.spring.util.exception;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import tw.com.oscar.spring.util.annotations.Log;
import tw.com.oscar.spring.util.validator.AccountValidator;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Title: CentralControllerAdvice.java<br>
 * </p>
 * <strong>Description:</strong> A controller advice for exception handler<br>
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
@ControllerAdvice
class CentralControllerAdvice {

    @Log
    Logger LOGGER;

    @Autowired
    private AccountValidator accountValidator;

    /**
     * A method used for binding AccountValidator validator
     *
     * @param binder a WebDataBinder object
     */
    @InitBinder("account")
    protected void initBinder(WebDataBinder binder) {
        LOGGER.info("[Enter] AccountController.initBinder");
        binder.setValidator(accountValidator);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }

    /**
     * A exception handler occurred when throwing Exception class
     *
     * @param exception  a Exception object
     * @param webRequest a WebRequest object
     * @return a ModelAndView object
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ModelAndView exception(Exception exception, WebRequest webRequest) {
        LOGGER.error(exception.getMessage(), exception);
        ModelAndView modelAndView = new ModelAndView("error/general");
        Principal principal = webRequest.getUserPrincipal();
        LOGGER.info(principal.getName());
        modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
        return modelAndView;
    }

    /**
     * A exception handler when account not found occurred
     *
     * @param exception a AccountNotFoundException object
     * @return a ResponseEntity object
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<String> handlePersonNotFound(AccountNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
