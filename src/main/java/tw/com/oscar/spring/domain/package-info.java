/**
 * <p>
 * Title: package-info.java<br>
 * </p>
 * <strong>Description:</strong> A configuration for using hibernate user type<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author oscarwei168
 * @version v1, 2015/8/1
 * @since 2015/8/1
 */
@TypeDefs({
        @TypeDef(name = "localDateType",
                defaultForType = LocalDate.class,
                typeClass = LocalDateUserType.class),
        @TypeDef(name = "localDateTimeType",
                defaultForType = LocalDateTime.class,
                typeClass = LocalDateTimeUserType.class),
        @TypeDef(name = "localTimeType",
                defaultForType = LocalTime.class,
                typeClass = LocalTimeUserType.class)
}) package tw.com.oscar.spring.domain;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import tw.com.oscar.spring.util.usertype.LocalDateTimeUserType;
import tw.com.oscar.spring.util.usertype.LocalDateUserType;
import tw.com.oscar.spring.util.usertype.LocalTimeUserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;