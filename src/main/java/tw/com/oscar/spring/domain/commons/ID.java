/**
 * ID.java
 * Title: Oscar Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/25
 * <p>
 * H i s t o r y
 * 2015/7/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.domain.commons;

import javax.persistence.*;

/**
 * <p>
 * Title: ID.java
 * </p>
 * <strong>Description:</strong> A class for declaring model primary key definition<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/25
 * @since 2015/7/25
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
class ID {

    protected Long id;

    /**
     * A getter for 'id' property
     *
     * @return a id value
     */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * A setting for 'id' property
     *
     * @param id a id value
     */
    private void setId(Long id) {
        this.id = id;
    }
}
