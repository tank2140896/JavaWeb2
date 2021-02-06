package com.javaweb.config.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDbConfig {
	
	/**
	 * If you do not use Spring Data MongoDB, 
	 * you can inject a MongoClient bean instead of using MongoDatabaseFactory. 
	 * If you want to take complete control of establishing the MongoDB connection, 
	 * you can also declare your own MongoDatabaseFactory or MongoClient bean.
	 */
	@Bean
    public MongoClient mongoClient(){
		//MongoCredential credential = MongoCredential.createCredential(username,db,password.toCharArray());
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/my");
        MongoClientSettings setting = MongoClientSettings.builder()
                                                       //.credential(credential)
                                                       .applyConnectionString(connectionString)
                                                       //.applyToClusterSettings(builder->builder.hosts(Arrays.asList(new ServerAddress(ipAddress(),port()))).mode(ClusterConnectionMode.SINGLE).requiredClusterType(ClusterType.STANDALONE))
                                                       .build();
        return MongoClients.create(setting);
    }
	
}
