/**
 * MongoConfig.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/8/6
 * <p>
 * H i s t o r y
 * 2015/8/6 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;

/**
 * <p>
 * Title: MongoConfig.java<br>
 * </p>
 * <strong>Description:</strong> A no-sql MongoDB configuration<br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/8/6
 * @since 2015/8/6
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(150).connectTimeout(1000 * 10)
                .writeConcern(WriteConcern.UNACKNOWLEDGED).build();
        ServerAddress serverAddress = new ServerAddress("127.0.0.1");
        MongoClient mongoClient = new MongoClient(serverAddress, options);
        // UserCredentials credentials = new UserCredentials("", "");
        return new SimpleMongoDbFactory(mongoClient, "oscar"); // database name
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate template = new MongoTemplate(mongoDbFactory(), mongoConverter());
        return template;
    }

    @Bean
    public MongoConverter mongoConverter() throws UnknownHostException {
        MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), mongoMappingContext());
        converter.setTypeMapper(mongoTypeMapper());
        return converter;
    }

    private MongoTypeMapper mongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }

    private MappingContext mongoMappingContext() {
        return new MongoMappingContext();
    }
}
