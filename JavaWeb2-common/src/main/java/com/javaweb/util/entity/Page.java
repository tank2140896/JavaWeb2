package com.javaweb.util.entity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.javaweb.util.core.PageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
  
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条 
	
	private Long totalSize = 0L;//默认一共0条数据
	
	private Long totalPage = 0L;//默认一共0页
	
	private Object data;//数据
	
	private List<Long> pageList;//分页页数
	
	@SuppressWarnings("unchecked")
	public Page(Object pageParam,Object data,Long totalSize){
		this.data = data;
		this.totalSize = totalSize;
		try{
			if(pageParam instanceof Map){//支持Map类型
				Map<Object,Object> map = (Map<Object,Object>)pageParam;
				this.currentPage = (Long)map.get("currentPage");
				this.pageSize = (Long)map.get("pageSize");
			}else{//支持Bean对象类型
				//这里通过反射虽然降低了速度,但是更具有通用性,只要请求参数pageParam带有currentPage和pageSize即可
				Class<?> cls = pageParam.getClass();
				Field fieldCurrentPage = cls.getDeclaredField("currentPage");
				Field fieldPageSize = cls.getDeclaredField("pageSize");
				fieldCurrentPage.setAccessible(true);  
				fieldPageSize.setAccessible(true);
				this.currentPage = (Long)fieldCurrentPage.get(pageParam);
				this.pageSize = (Long)fieldPageSize.get(pageParam);
			}
		}catch(Exception e){
			//do nothing
		}
		this.totalPage = PageUtil.getTotalPage(totalSize,pageSize);
		this.pageList = PageUtil.getShowPages(currentPage,totalPage,5L);
	}

}
