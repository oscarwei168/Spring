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
 * Created by Oscar on 2015/2/25.
 */
public class DateFormatter implements Formatter<Date> {

    @Autowired
    private MessageSource messageSource;

    public DateFormatter() {
        super();
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        final DateFormat df = createDateFormat(locale);
        return df.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        final DateFormat df = createDateFormat(locale);
        return df.format(object);
    }

    private DateFormat createDateFormat(final Locale locale) {
        final String format = this.messageSource.getMessage("date.format", null, locale);
        final DateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        return sdf;
    }
}
