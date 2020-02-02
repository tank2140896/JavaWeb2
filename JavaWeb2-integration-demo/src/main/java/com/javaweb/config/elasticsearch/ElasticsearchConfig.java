package com.javaweb.config.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

	@Bean(name="transportClient")
	public TransportClient transportClient() throws UnknownHostException {
		TransportAddress node = new TransportAddress(InetAddress.getByName("192.168.0.102"),9200);
		Settings settings = Settings.builder().put("cluster.name","elasticsearch").build();
		TransportClient transportClient = new PreBuiltTransportClient(settings);
		transportClient.addTransportAddress(node);
		return transportClient;
	}

}
