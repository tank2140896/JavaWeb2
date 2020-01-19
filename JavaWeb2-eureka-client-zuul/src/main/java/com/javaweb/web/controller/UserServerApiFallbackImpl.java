package com.javaweb.web.controller;

import org.springframework.stereotype.Component;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.CommonConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.web.eo.UserLoginRequest;

@Component
public class UserServerApiFallbackImpl implements UserServerApi {

    public BaseResponseResult webLogin(UserLoginRequest userLoginRequest) {
        return new BaseResponseResult(HttpCodeEnum.INTERNAL_ERROR.getCode(),"系统异常",CommonConstant.EMPTY_VALUE);
    }

}
