package com.javaweb.config.hbase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HbaseConfig {
	
	@Value("${hbase.zookeeper.quorum}")
	private String hbaseZookeeperQuorum;

	public String getHbaseZookeeperQuorum() {
		return hbaseZookeeperQuorum;
	}

	public void setHbaseZookeeperQuorum(String hbaseZookeeperQuorum) {
		this.hbaseZookeeperQuorum = hbaseZookeeperQuorum;
	}
    
    @Bean
    public HbaseHandleService getHBaseService() {
    	org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum",hbaseZookeeperQuorum) ;
        return new HbaseHandleService(configuration);
    }

}
