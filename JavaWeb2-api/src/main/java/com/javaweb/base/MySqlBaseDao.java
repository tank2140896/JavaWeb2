package com.javaweb.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.javaweb.constant.CommonConstant;

@Mapper
public interface MySqlBaseDao<T> {
	
	@Insert(CommonConstant.EMPTY_VALUE)
	public Integer insert(T t);//插入实体类
	
	@Update(CommonConstant.EMPTY_VALUE)
	public Integer update(T t);//更新实体类
	
	@Delete(CommonConstant.EMPTY_VALUE)
	public Integer delete(Object id);//根据主键删除
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAll();//查询所有
	
	@Select(CommonConstant.EMPTY_VALUE)
	public Long selectAllCount();//统计所有
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAllByPaging(Map<String,Long> map);//分页查询
	
	@Select(CommonConstant.EMPTY_VALUE)
	public T selectByPk(Object id);//根据主键查询
	
}