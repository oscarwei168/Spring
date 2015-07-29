/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: CustomNamingStrategy
 *
 * @author Oscar Wei
 * @since 2015/3/7
 *
 * H i s t o r y
 *
 * 2015/3/7 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.spring.util.namingstrategy;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * <strong>Description:</strong><br>
 * This function include: - a hibernate naming strategy for upper case tables and fields name <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/7
 * @since 2015/3/7
 */
public class UpperCaseNamingStrategy extends ImprovedNamingStrategy {

    /**
     * A function method for upper case the table name
     *
     * @param tableName a table name that will be upper case
     * @return a string which always upper the table name
     */
    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName).toUpperCase();
    }

    /**
     * A function method for upper case the table's column names
     *
     * @param columnName a table columns name
     * @return a string which always upper the column name
     */
    @Override
    public String columnName(String columnName) {
        return super.columnName(columnName).toUpperCase();
    }

    /**
     * A function method for upper case the properties name
     *
     * @param propertyName a entity class properties name
     * @return a string which always upper the properties name
     */
    @Override
    public String propertyToColumnName(String propertyName) {
        return super.propertyToColumnName(propertyName).toUpperCase();
    }
}
