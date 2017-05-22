package com.javaweb.dataobject.eo;

import com.javaweb.help.ResponseResult;

public class CheckData {
	
	private boolean checkFlag = false;
	
	private TokenData tokenData = null;
	
	private ResponseResult responseResult = null;;

	public boolean isCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public TokenData getTokenData() {
		return tokenData;
	}

	public void setTokenData(TokenData tokenData) {
		this.tokenData = tokenData;
	}

	public ResponseResult getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(ResponseResult responseResult) {
		this.responseResult = responseResult;
	}
	
}
