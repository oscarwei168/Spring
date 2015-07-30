/**
 * GenericDAOImpl.java
 * Title: Oscar Web Wei Project
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
 * Title: GenericDAOImpl.java<br>
 * </p>
 * <strong>Description:</strong> A generic DAO concrete implementation <br>
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
public class GenericDAOImpl<T, PK extends Serializable> extends HibernateBaseDAO implements GenericDAO<T, PK> {

    @Override
    public T find(PK id) {
        return null;
    }
    
    @Override
    public Stream<T> find(PK... ids) {
        return null;
    }
}
