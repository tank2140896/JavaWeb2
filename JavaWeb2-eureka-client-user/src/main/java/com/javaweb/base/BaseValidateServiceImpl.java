package com.javaweb.base;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaweb.constant.CommonConstant;
import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.db.query.QueryWapper;

@Service("baseValidateServiceImpl")
public class BaseValidateServiceImpl extends BaseService implements BaseValidateService {
	
	public <T> BaseServiceValidateResult isColumnsRepeat(Map<String,Object> map,DaoWapper<T> daoWapper,String message){
		BaseServiceValidateResult baseServiceValidateResult = new BaseServiceValidateResult();
		baseServiceValidateResult.setValidatePass(true);
		String returnMessage = null;
		if((message!=CommonConstant.NULL_VALUE)&&(!CommonConstant.EMPTY_VALUE.equals(message.trim()))){
			try{returnMessage = getMessage(message);}catch(Exception e){}	
		}
		if(returnMessage==CommonConstant.NULL_VALUE||CommonConstant.EMPTY_VALUE.equals(returnMessage.trim())){
			baseServiceValidateResult.setMessage(message);
		}else{
			baseServiceValidateResult.setMessage(returnMessage);
		}
		if(map!=null&&map.size()>0){
			QueryWapper<T> queryWapper = new QueryWapper<>();
			for(String key:map.keySet()){
				queryWapper.eq(key,map.get(key));
			}
			List<T> list = daoWapper.selectList(queryWapper);
			if(list!=null&&list.size()>0){
				baseServiceValidateResult.setValidatePass(false);
			}
		}
		return baseServiceValidateResult;
	}

}
