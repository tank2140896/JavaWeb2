package com.javaweb.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//JDK动态代理（基于接口）
public class JdkDynamicProxy implements InvocationHandler {
	
	private static final JdkDynamicProxy JDK_DYNAMIC_PROXY = new JdkDynamicProxy();
	
	private JdkDynamicProxy(){ }
    
    @SuppressWarnings("unchecked")
	public static <T>T proxy(Class<T> interfaceClass){
    	return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{interfaceClass},JDK_DYNAMIC_PROXY); 
    }

	public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
		//System.out.println(method.getName());
		return null;
	}

}
