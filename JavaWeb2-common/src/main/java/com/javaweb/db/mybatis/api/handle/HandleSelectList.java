package com.javaweb.db.mybatis.api.handle;

import com.javaweb.db.help.SqlBuildInfo;
import com.javaweb.db.help.SqlHandle;
import com.javaweb.enums.DbTypeEnum;
import com.javaweb.exception.ServiceException;

public class HandleSelectList implements SqlHandle {

	public String handle(SqlBuildInfo sqlBuildInfo) {
		if(DbTypeEnum.MYSQL.equals(sqlBuildInfo.getDbTypeEnum())){
			return mySqlForSqlString.getSelectListStringSql(sqlBuildInfo);
		}else{
			throw new ServiceException("目前只支持mysql数据库");
		}
	}

}
