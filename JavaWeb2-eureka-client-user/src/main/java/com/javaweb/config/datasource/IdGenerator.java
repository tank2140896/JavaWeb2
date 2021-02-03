package com.javaweb.config.datasource;

import org.springframework.stereotype.Component;

import com.javaweb.constant.SystemConstant;
import com.javaweb.db.help.IdAutoCreate;
import com.javaweb.util.core.SecretUtil;

@Component
public class IdGenerator implements IdAutoCreate<String> {

	//ID自动生成策略
	public String idCreate() {
		return SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO);
	}

}
