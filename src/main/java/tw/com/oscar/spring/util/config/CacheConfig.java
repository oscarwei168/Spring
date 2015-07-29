/**
 * CacheConfig.java
 * Title: Oscar Wei Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/27
 * <p>
 * H i s t o r y
 * 2015/7/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import tw.com.oscar.spring.util.annotation.DevProfile;
import tw.com.oscar.spring.util.annotation.ProdProfile;
import tw.com.oscar.spring.util.cache.EnhancedDefaultKeyGenerator;

/**
 * <p>
 * Title: CacheConfig.java
 * </p>
 * <strong>Description:</strong> A class used for initial Ehcache cache manager<br>
 * This function include: - <br>
 * <ul><li>Initial Ehcache cache manager</li></ul>
 * <ul><li>A custom cache key generator strategy</li></ul>
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
@Configuration
@EnableCaching()
public class CacheConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * A bean for obtain 'production' environment Ehcache cache manager
     *
     * @return a CacheManager object
     */
    @Bean
    @ProdProfile
    public CacheManager cacheManager() {
        LOGGER.info("Cache manager is ehCacheCacheManager");
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    /**
     * A bean for obtain initialize EhCacheManagerFactoryBean object
     *
     * @return a EhCacheManagerFactoryBean object
     */
    @Bean
    @ProdProfile
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cache = new EhCacheManagerFactoryBean();
        cache.setConfigLocation(new ClassPathResource("cache/ehcache.xml"));
        cache.setShared(true);
        return cache;
    }

    /**
     * A bean for customize cache key generation strategy
     *
     * @return a KeyGenerator object
     */
    @Bean
    @ProdProfile
    public KeyGenerator enhancedDefaultKeyGenerator() {
        return new EnhancedDefaultKeyGenerator();
    }

    /**
     * A bean for obtain 'development' environment cache manager
     *
     * @return a CacheManager object
     */
    @Bean
    @DevProfile
    public CacheManager concurrentMapCacheManager() {
        LOGGER.info("Cache manager is ConcurrentMapCacheManager");
        return new ConcurrentMapCacheManager("test-cache");
    }
}
