package com.javaweb.mybatis.api;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.javaweb.constant.CommonConstant;

@Mapper
public interface DaoForOracle<T> {
	
	@Insert(CommonConstant.EMPTY_VALUE)
	public Integer insertForOracle(T t);//插入实体类
	
	@Update(CommonConstant.EMPTY_VALUE)
	public Integer updateForOracle(T t);//更新实体类
	
	@Delete(CommonConstant.EMPTY_VALUE)
	public Integer deleteForOracle(Object id);//根据主键删除
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAllForOracle();//查询所有
	
	@Select(CommonConstant.EMPTY_VALUE)
	public Long selectAllCountForOracle();//统计所有
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAllByPagingForOracle(Map<String,Long> map);//分页查询(map的两个参数为:currentPage和pageSize)
	
	@Select(CommonConstant.EMPTY_VALUE)
	public T selectByPkForOracle(Object id);//根据主键查询
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectByConditionForOracle(Map<String,Object> map);//根据条件查询(不推荐使用)
	
}