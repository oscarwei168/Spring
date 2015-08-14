/**
 * AccountListXML.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/14
 * <p>
 * H i s t o r y
 * 2015/8/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.pojo.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: AccountListXML.java<br>
 * </p>
 * <strong>Description:</strong> A XML account object<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/14
 * @since 2015/8/14
 */
@XmlRootElement(name = "accounts")
@Data
public class AccountListXML implements Serializable {

    List<AccountXML> accounts = new ArrayList<>();

}
