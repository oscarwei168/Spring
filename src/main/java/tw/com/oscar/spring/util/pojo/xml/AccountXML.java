/**
 * AccountXML.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/13
 * <p>
 * H i s t o r y
 * 2015/8/13 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.pojo.xml;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * <p>
 * Title: AccountXML.java<br>
 * </p>
 * <strong>Description:</strong> A account JAXB object<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/13
 * @since 2015/8/13
 */
@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.NONE)
@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountXML implements Serializable {

    @XmlAttribute
    private Long id;
    @XmlElement
    private String username;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String email;

}
