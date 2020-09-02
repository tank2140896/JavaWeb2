package com.javaweb.mybatis.api;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.javaweb.constant.CommonConstant;
import com.javaweb.query.QueryWapper;

@Mapper
public interface DaoForMySql<T> {
	
	@Insert(CommonConstant.EMPTY_VALUE)
	public Integer insertForMySql(T t);//插入实体类
	
	@Update(CommonConstant.EMPTY_VALUE)
	public Integer updateForMySql(T t);//更新实体类
	
	@Delete(CommonConstant.EMPTY_VALUE)
	public Integer deleteForMySql(Object id);//根据主键删除
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAllForMySql();//查询所有
	
	@Select(CommonConstant.EMPTY_VALUE)
	public Long selectAllCountForMySql();//统计所有
	
	@Select(CommonConstant.EMPTY_VALUE)
	public T selectByPkForMySql(Object id);//根据主键查询

	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectAllByPagingForMySql(Map<String,Long> map);//分页查询(map的两个参数为:currentPage和pageSize)
	
	@Select(CommonConstant.EMPTY_VALUE)
	public List<T> selectListForMySql(QueryWapper<T> queryWapper);//条件查询
	
}
