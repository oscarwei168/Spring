package tw.com.oscar.spring.util.config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
// @EnableJpaRepositories(basePackageClasses = Application.class)
//@Profile("test")
class JpaConfig implements TransactionManagementConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaConfig.class);

    @Value("${db.driverClassName}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

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
    @Value("${hikari.transactionIsolation}")
    private String transactionIsolation;

    @Value("${entitymanager.packages.to.scan}")
    private String entitymanagerPackagesScan;

    /**
     * A method for obtain MySQL data source
     *
     * @return a MysqlConnectionPoolDataSource object
     */
    @Bean
    @Description("This is used for MySQL data source")
    public MysqlConnectionPoolDataSource mysqlDataSource() {
        Objects.requireNonNull(url);
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource(); // MySqlDataSource
        ds.setURL(url);
        ds.setUser(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean(destroyMethod = "close")
    public HikariDataSource hikariDataSource() {
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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(hikariDataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {entitymanagerPackagesScan});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
