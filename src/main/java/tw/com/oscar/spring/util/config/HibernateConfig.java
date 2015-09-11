/**
 * HibernateConfig.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/25
 * <p>
 * H i s t o r y
 * 2015/7/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tw.com.oscar.spring.util.annotations.DevProfile;
import tw.com.oscar.spring.util.namingstrategy.UpperCaseNamingStrategy;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * Title: HibernateConfig.java
 * </p>
 * <strong>Description:</strong> A spring component that initial spring mvc features <br>
 * This function include: - <br>
 * <ul><li>Initialize MySQL data source</li></ul>
 * <ul><li>Initialize HikariCP data source</li></ul>
 * <ul><li>Construct Hibernate session factory</li></ul>
 * <ul><li>Construct Hibernate transaction manager</li></ul>
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
@Configuration
@EnableTransactionManagement
@DevProfile
public class HibernateConfig {

    @Value("${db.driverClassName}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Value("${db1.driverClassName}")
    private String dbDriver;
    @Value("${db1.url}")
    private String dbUrl;
    @Value("${db1.username}")
    private String dbUsername;
    @Value("${db1.password}")
    private String dbPassword;

    @Value("${hikari.autoCommit}")
    private boolean autoCommit;
    @Value("${hikari.connectionTimeout}")
    private long connectionTimeout;
    @Value("${hikari.idleTimeout}")
    private long idleTimeout;
    @Value("${hikari.maxLifetime}")
    private long maxLifetime;
    @Value("${hikari.maximumPoolSize}")
    private int maxPoolSize;
    @Value("${hikari.cachePrepStmts}")
    private boolean cachePrepStmts;
    @Value("${hikari.prepStmtCacheSize}")
    private int prepStmtCacheSize;
    @Value("${hikari.prepStmtCacheSqlLimit}")
    private int prepStmtCacheSqlLimit;
    @Value("${hikari.transactionIsolation}")
    private String transactionIsolation;

    @Value("${entitymanager.packages.to.scan}")
    private String entitymanagerPackagesScan;

    /**
     * A method for obtain MySQL data source
     *
     * @return a MysqlConnectionPoolDataSource object
     * @throws SQLException throw exception when:<br>
     *                      <ul><li>prepare statement cache size</li></ul>
     */
    @Bean
    @Description("This is used for MySQL data source")
    public MysqlConnectionPoolDataSource mysqlDataSource() throws SQLException {
        Objects.requireNonNull(url);
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource(); // MySqlDataSource
        ds.setURL(url);
        ds.setUser(username);
        ds.setPassword(password);
        ds.setCachePrepStmts(cachePrepStmts);
        ds.setPreparedStatementCacheSize(prepStmtCacheSize);
        ds.setPreparedStatementCacheSqlLimit(prepStmtCacheSqlLimit);
        return ds;
    }

    /**
     * A method for obtain second MySQL data source
     *
     * @return a MysqlConnectionPoolDataSource object
     * @throws SQLException throw exception when:<br>
     *                      <ul><li>prepare statement cache size</li></ul>
     */
    @Bean
    @Description("This is second MySQL data source")
    public MysqlConnectionPoolDataSource mysql2DataSource() throws SQLException {
        Objects.requireNonNull(dbDriver);
        Objects.requireNonNull(dbUrl);
        Objects.requireNonNull(dbUsername);
        Objects.requireNonNull(dbPassword);
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl(dbUrl);
        ds.setUser(dbUsername);
        ds.setPassword(dbPassword);
        ds.setCachePrepStmts(cachePrepStmts);
        ds.setPreparedStatementCacheSize(prepStmtCacheSize);
        ds.setPreparedStatementCacheSqlLimit(prepStmtCacheSqlLimit);
        return ds;
    }

    /**
     * A method for obtain HikariCP data pooling that wrapping Oracle data source internally
     *
     * @return a HikariDataSource object
     * @throws SQLException throw exception when:<br>if any exception occurred
     */
    @Bean(destroyMethod = "close")
    @DependsOn("mysqlDataSource")
    public DataSource dataSource() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDataSource(mysqlDataSource());
        ds.setAutoCommit(autoCommit);
        ds.setConnectionTimeout(connectionTimeout);
        ds.setIdleTimeout(idleTimeout);
        ds.setMaxLifetime(maxLifetime);
        ds.setMaximumPoolSize(maxPoolSize);
        ds.setTransactionIsolation(transactionIsolation);
        return ds;
    }

    /**
     * A method for obtain HikariCP data pooling that wrapping Oracle data source internally
     *
     * @return a HikariDataSource object
     * @throws SQLException throw exception when:<br>if any exception occurred
     */
    @Bean(destroyMethod = "close")
    public HikariDataSource hikari2DataSource() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setDataSource(mysql2DataSource());
        ds.setAutoCommit(autoCommit);
        ds.setConnectionTimeout(connectionTimeout);
        ds.setIdleTimeout(idleTimeout);
        ds.setMaxLifetime(maxLifetime);
        ds.setMaximumPoolSize(maxPoolSize);
        ds.setTransactionIsolation(transactionIsolation);
        return ds;
    }

    /**
     * A method for obtain Hibernate SessionFactory object
     *
     * @return a LocalSessionFactoryBean object
     * @throws SQLException throw exception when:<br>if any exception occurred
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(entitymanagerPackagesScan);
        factoryBean.setNamingStrategy(new UpperCaseNamingStrategy());
        factoryBean.setHibernateProperties(hibernateProperties());
        return factoryBean;
    }

    /**
     * A method to obtain a properties object for Hibernate usage
     *
     * @return a Properties object
     */
    Properties hibernateProperties() {
        return new Properties() {
            {
                // setProperty(Environment.SHOW_SQL, "true");
                // setProperty(Environment.FORMAT_SQL, "true");
                // setProperty(Environment.USE_QUERY_CACHE, "true");
            }
        };
    }

    /**
     * A method for obtain Hibernate transaction manager
     *
     * @param sessionFactory a SessionFactory object
     * @return a HibernateTransactionManager object
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    /**
     * A bean to translate exception used by a post-processor
     *
     * @return a PersistenceExceptionTranslationPostProcessor object
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
