package com.javaweb.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerListener {
	
	@KafkaListener(topics={"a1","a2","a3"})
    public void listen_A(ConsumerRecord<String,String> record) {
        
    }
	
	@KafkaListener(topics={"b"})
    public void listen_B(ConsumerRecord<?,?> record) {
        
    }
	
	@KafkaListener(topics="c")
    public void listen_C(ConsumerRecord<String,String> record) {
        
    }

}
