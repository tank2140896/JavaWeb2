package com.javaweb.handler;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseTool;
import com.javaweb.constant.CommonConstant;
import com.javaweb.util.core.ObjectOperateUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;

@RestControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<BaseResponseResult>{
	
	public BaseResponseResult beforeBodyWrite(BaseResponseResult baseResponseResult,MethodParameter methodParameter,MediaType mediaType,
		   Class<? extends HttpMessageConverter<?>> c,ServerHttpRequest serverHttpRequest,ServerHttpResponse serverHttpResponse) {
		String path = serverHttpRequest.getURI().getPath();
		if(path.startsWith("/web")){//目前带权限的接口会处理数据权限
			try {
				TokenData tokenData = BaseTool.getTokenData(BaseTool.getToken(serverHttpRequest));
				if(tokenData!=null){
					List<ExcludeInfoResponse> excludeInfoResponseList = tokenData.getExcludeInfoResponseList();
					if(excludeInfoResponseList!=null&&excludeInfoResponseList.size()>0){
						for(int i=0;i<excludeInfoResponseList.size();i++){
							ExcludeInfoResponse excludeInfoResponse = excludeInfoResponseList.get(i);
							if(path.equals(excludeInfoResponse.getUrl())){
								String out = ObjectOperateUtil.excludeField(baseResponseResult,excludeInfoResponse.getExcludeField().split(CommonConstant.COMMA),false);
								baseResponseResult = new ObjectMapper().readValue(out,BaseResponseResult.class);
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				//do nothing
			}
		}
		return baseResponseResult;
	}

	public boolean supports(MethodParameter methodParameter,Class<? extends HttpMessageConverter<?>> c) {
		return true;
	}

}
