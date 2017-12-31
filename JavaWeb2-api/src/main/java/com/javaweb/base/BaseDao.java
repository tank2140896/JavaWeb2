package com.javaweb.base;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.javaweb.constant.CommonConstant;

@Mapper
public interface BaseDao<T> {
	
	@Insert(CommonConstant.EMPTY_VALUE)
	public Integer insert(T t);
	
	@Update(CommonConstant.EMPTY_VALUE)
	public Integer update(T t);
	
	@Delete(CommonConstant.EMPTY_VALUE)
	public Integer delete(T t);
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAll(Class<T> c);
	
	@Select(CommonConstant.EMPTY_VALUE)
	public T selectByPk(T t);
	
}