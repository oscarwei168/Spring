/**
 * DateFormatter.java
 * Title: Oscar Wei Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/25
 * <p>
 * H i s t o r y
 * 2015/7/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * Title: DateFormatter.java<br>
 * </p>
 * <strong>Description:</strong> A date formatter for spring framework registration<br>
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
public class DateFormatter implements Formatter<Date> {

    @Autowired
    private MessageSource messageSource;

    /**
     * A default constructor
     */
    public DateFormatter() {
        super();
    }

    /**
     * A method used for parsing input text as Date object
     *
     * @param text   a input text
     * @param locale a Locale object
     * @return a Date object
     * @throws ParseException throw exception when:<br>if any exception occurred
     */
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        final DateFormat df = createDateFormat(locale);
        return df.parse(text);
    }

    /**
     * A method used for formatting input date object
     *
     * @param object a Date object
     * @param locale a Locale object
     * @return a formatted value
     */
    @Override
    public String print(Date object, Locale locale) {
        final DateFormat df = createDateFormat(locale);
        return df.format(object);
    }

    /**
     * Obtaining a DateFormat object according to message resource definition
     *
     * @param locale a Locale object
     * @return a DateFormat object
     */
    private DateFormat createDateFormat(final Locale locale) {
        final String format = this.messageSource.getMessage("date.format", null, locale);
        final DateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        return sdf;
    }
}
