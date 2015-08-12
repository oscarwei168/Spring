/**
 * Value.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/12
 * <p>
 * H i s t o r y
 * 2015/8/12 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * <p>
 * Title: Value.java<br>
 * </p>
 * <strong>Description:</strong> A lombok POJO example<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/12
 * @since 2015/8/12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private Long id;
    private String quote;
}
