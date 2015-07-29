/**
 * IBaseDao.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/27
 * <p>
 * H i s t o r y
 * 2015/7/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.common.dao;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: GenericDao.java
 * </p>
 * <strong>Description:</strong> A interface that defining all common methods for abstract DAO class usage<br>
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
public interface IBaseDao<T, PK extends Serializable> {

    /**
     * A method for searching all entities without any conditions
     *
     * @return streaming of specific entities
     */
    Stream<T> findAll();

    /**
     * A method for searching specific entities with any criterion
     *
     * @param criterionList list of Criterion object
     * @return list of specific entities
     */
    List<T> find(Criterion... criterionList);

    /**
     * A method for searching specific entity by condition with PK value
     *
     * @param pid a PK value
     * @return a entity object
     */
    Optional<T> findByPid(final PK pid);

    /**
     * A method for searching specific entity by condition with PK value
     *
     * @param pid a PK value
     * @return a entity object
     */
    Optional<T> loadByPid(final PK pid);

    /**
     * A method for persisting a specific entity
     *
     * @param entity a entity object
     */
    void create(final T entity);

    /**
     * A method for updating a specific entity
     *
     * @param entity a entity object
     * @return a entity object
     */
    T update(final T entity);

    /**
     * A method for deleting a specific entity
     *
     * @param entity a entity object
     */
    void delete(final T entity);

    /**
     * A method for persisting a specific entity by condition with PK value
     *
     * @param pid a PK value
     */
    void delete(final PK pid);

}
