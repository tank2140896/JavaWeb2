package com.javaweb.config.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

@Service("itemWriterService")
public class ItemWriterService implements ItemWriter<String> {

	//数据写入
	@Override
	public void write(List<? extends String> list) throws Exception {
		for(String str:list){
			System.out.println("写入："+str);
		}
	}

}
