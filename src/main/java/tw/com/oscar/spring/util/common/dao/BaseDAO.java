/**
 * BaseDAO.java
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
package tw.com.oscar.spring.util.common.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>
 * Title: BaseDAO.java<br>
 * </p>
 * <strong>Description:</strong> A base DAO class for Hibernate session object injected <br>
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
public class BaseDAO<T, ID extends Serializable> extends com.googlecode.genericdao.dao.hibernate.GenericDAOImpl<T, ID> {

    /**
     * A method for injecting Hibernate SessionFactory object
     *
     * @param sessionFactory a SessionFactory object
     */
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
