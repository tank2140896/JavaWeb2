package com.javaweb.config.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
windows下安装操作：
下载地址：https://www.elastic.co/cn/downloads/
分词插件（放入位置：elasticsearch下的plugins目录下）下载地址：https://github.com/medcl/elasticsearch-analysis-ik/releases
参考文档：https://docs.spring.io/spring-data/elasticsearch/docs/4.0.0.RELEASE/reference/html/#reference
数据采集传输可以使用：Logstash
*/
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

	@Bean
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
        return RestClients.create(clientConfiguration).rest();
	}

}
