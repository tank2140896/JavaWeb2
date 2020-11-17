package com.javaweb.config.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;

@Service("itemReaderService")
public class ItemReaderService implements ItemReader<String> {
	
	private String[] message = {"a","b","c","d","e"};
	private int count = 0;
	
	//数据读取
	@Override
	public String read() throws Exception {
		if(count<message.length){
			return message[count++];
		}
		count = 0;
		return null;
	}

}
