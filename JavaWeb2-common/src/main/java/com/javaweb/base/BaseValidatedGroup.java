package com.javaweb.base;

//基类校验组，所有实体类（业务、自定义等）均可继承该类
public class BaseValidatedGroup {
	
	//新增校验组
	public interface add{}
	
	//删除校验组
	public interface delete{}
	
	//修改校验组
	public interface update{}
	
	//查询校验组
	public interface select{}
	
	//上传校验组
	public interface upload{}
	
	//下载校验组
	public interface download{}
	
}
