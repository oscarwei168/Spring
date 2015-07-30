/**
 * HibernateBaseDAO.java
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

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * <p>
 * Title: HibernateBaseDAO.java<br>
 * </p>
 * <strong>Description:</strong> A hibernate base DAO implementation<br>
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
public class HibernateBaseDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        // searchProcessor = HibernateSearchProcessor.getInstanceForSessionFactory(sessionFactory);
        // metadataUtil = HibernateMetadataUtil.getInstanceForSessionFactory(sessionFactory);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Get the current Hibernate session
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Serializable _save(Object entity) {
        return getSession().save(entity);
    }

    protected void _save(Object... entities) {
        for (Object entity : entities) {
            _save(entity);
        }
    }

    protected void _saveOrUpdate(Object entity) {
        getSession().saveOrUpdate(entity);
    }

//    protected boolean _saveOrUpdateIsNew(Object entity) {
//        Preconditions.checkArgument(null != entity, "attempt to saveOrUpdate with null entity");
//
//    }

}
