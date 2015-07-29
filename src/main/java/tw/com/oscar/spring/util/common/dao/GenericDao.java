/**
 * GenericDao.java
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

import com.google.common.base.Preconditions;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: GenericDao.java
 * </p>
 * <strong>Description:</strong> A generic DAO class for defining all common CRUD methods<br>
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
public abstract class GenericDao<T, PK extends Serializable> extends AbstractDao {

    protected T instance;
    private Class<T> entityClass;

    /**
     * A default constructor to fulfill obtains entity class type using reflection
     */
    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]; // [1] will be PK
    }

    /**
     * @see IBaseDao#findAll() method
     */
    // @Override
    public Stream<T> findAll() {
        return this.getCurrentSession().createCriteria(this.getEntityClass())
                .list().stream();
    }

    /**
     * @see IBaseDao#find(Criterion...) method
     */
    // @Override
    @SuppressWarnings("unchecked")
    public List<T> find(Criterion... criterionList) {
        Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());
        for (Criterion c : criterionList) {
            criteria.add(c);
        }
        return criteria.list();
    }

    /**
     * @see IBaseDao#findByPid(Serializable) method
     */
    // @Override
    @SuppressWarnings("unchecked")
    public Optional<T> findByPid(final PK pid) {
        Preconditions.checkArgument(null != pid);
        return Optional.ofNullable((T) this.getCurrentSession().get(this.getEntityClass(), pid));
    }

    /**
     * @see IBaseDao#loadByPid(Serializable) method
     */
    // @Override
    @SuppressWarnings("unchecked")
    public Optional<T> loadByPid(final PK pid) {
        Preconditions.checkArgument(null != pid);
        return Optional.ofNullable((T) this.getCurrentSession().load(this.getEntityClass(), pid));
    }

    /**
     * @see IBaseDao#create(Object) method
     */
    // @Override
    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        this.getCurrentSession().persist(entity);
    }

    /**
     * @see IBaseDao#update(Object) method
     */
    // @Override
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        this.getCurrentSession().merge(entity);
        return entity;
    }

    /**
     * @see IBaseDao#delete(Object) method
     */
    // @Override
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        this.getCurrentSession().delete(entity);
    }

    /**
     * @see IBaseDao#delete(Serializable) method
     */
    // @Override
    public void delete(final PK pid) {
        final Optional<T> entity = this.findByPid(pid);
        Preconditions.checkState(null != entity);
        entity.orElseThrow(() -> new IllegalArgumentException("Cannot find any entities, pid : " + pid));
        this.delete(entity.get());
    }

    /**
     * A method for obtain current entity object
     *
     * @return a Class that specific for a entity
     */
    public Class<T> getEntityClass() {
        return this.entityClass;
    }

    //    @Autowired
    //    SessionFactory sessionFactory;

//    /**
//     * A method for obtain current Hibernate session object
//     *
//     * @return a Session object
//     */
//    protected final Session getCurrentSession() {
//        return this.sessionFactory.getCurrentSession();
//    }

    //    public void setEntityClass(final Class<T> entityClass) {
//        this.entityClass = entityClass;
//    }

//    protected Class<T> getEntityClass() throws Exception {
//        if (entityClass == null) {
//            Type type = getClass().getGenericSuperclass();
//            if (type instanceof ParameterizedType) {
//                ParameterizedType paramType = (ParameterizedType) type;
//                if (paramType.getActualTypeArguments().length == 2) {
//                    if (paramType.getActualTypeArguments()[1] instanceof TypeVariable) {
//                        throw new IllegalArgumentException(
//                                "Can't find entity using reflection");
//                    } else {
//                        entityClass = (Class<T>) paramType.getActualTypeArguments()[1];
//                    }
//                } else {
//                    entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
//                }
//            } else {
//                throw new Exception("Can't find entity using reflection");
//            }
//        }
//        return entityClass;
//    }

}
