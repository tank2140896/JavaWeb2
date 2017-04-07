package com.javaweb.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	
	public void save(T t);
	
	public void saveBatch(List<T> list);
	
	public int update(T t);
	
	public int updateBatch(List<T> list);
	
	public int delete(Object id);
	
	public int deleteBatch(Object[] id);

	public List<List<?>> list(Map<String,Object> map);
	
}
