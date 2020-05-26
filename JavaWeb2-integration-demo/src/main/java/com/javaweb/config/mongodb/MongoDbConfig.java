package com.javaweb.config.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

@Configuration
public class MongoDbConfig {
	
	@Bean
    public GridFSBucket getGridFSBucket(){
		MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase database = client.getDatabase("my");
        GridFSBucket gridFSBucket = GridFSBuckets.create(database);
        client.close();
        return gridFSBucket;
    }
	
}
