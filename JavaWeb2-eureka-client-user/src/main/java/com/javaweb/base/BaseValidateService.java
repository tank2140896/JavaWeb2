package com.javaweb.base;

import java.util.List;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.eo.validate.ColumnsRepeatRequest;

public interface BaseValidateService {
	
	//校验字段是否重复
	public <T> BaseServiceValidateResult isColumnsValueRepeat(ColumnsRepeatRequest<T> columnsRepeatRequest,DaoWapper<T> daoWapper);
	
	public <T> BaseServiceValidateResult isColumnsValueRepeat(List<ColumnsRepeatRequest<T>> columnsRepeatRequestList,DaoWapper<T> daoWapper);
	
	//可以追加其它自定义的业务校验

}
