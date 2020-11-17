package com.javaweb.config.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service("itemProcessorService")
public class ItemProcessorService implements ItemProcessor<String,String> {

	//数据处理
	@Override
	public String process(String str) throws Exception {
		return str.toUpperCase();
	}

}
