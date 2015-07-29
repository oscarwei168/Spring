/**
 * AbstractDao.java
 * Title: Oscar Wei Web Project
 * Copyright: (c) 2015, oscarwei168 Inc.
 * Name: AbstractDao
 *
 * @author Oscar Wei
 * @since 2015/3/8
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.common.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Title: AbstractDao.java
 * </p>
 * <strong>Description:</strong> A abstract class for spring DAO layer<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/27
 * @since 2015/7/27
 */
public abstract class AbstractDao {
    
    @Autowired
    SessionFactory sessionFactory;

    /**
     * A method for obtain Hibernate session object
     *
     * @return a Session object
     */
    protected final Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
