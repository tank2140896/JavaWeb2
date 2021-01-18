package com.javaweb.base;

import java.util.Map;

import com.javaweb.db.mybatis.api.DaoWapper;

public interface BaseValidateService {
	
	//校验字段是否重复
	public <T> BaseServiceValidateResult isColumnsRepeat(Map<String,Object> map,DaoWapper<T> daoWapper,String message);
	
	//可以追加其它自定义的业务校验

}
