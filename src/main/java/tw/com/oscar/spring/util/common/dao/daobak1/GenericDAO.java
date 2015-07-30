/**
 * GenericDAO.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/30
 * <p>
 * H i s t o r y
 * 2015/7/30 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.common.dao.daobak1;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * <p>
 * Title: GenericDAO.java<br>
 * </p>
 * <strong>Description:</strong> A generic DAO methods definition <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/30
 * @since 2015/7/30
 */
public interface GenericDAO<T, PK extends Serializable> {

    /**
     * A method for searching entity by pid
     *
     * @param id a id
     * @return a entity object
     */
    T find(PK id);

    /**
     * A method for searching entities by ids
     *
     * @param ids list of id value
     * @return streaming of entities object
     */
    Stream<T> find(PK... ids);

}
