package com.javaweb.util.core;

public class HashUtil {
	
	//除法散列法(m的选取不应为2的幂)
	public static long divisionMethod(long key,long m) {
		return key%m;
	}
	
	//乘法散列法
	public static long multiplicativeHashMethod(long key,double a,long m) {
		double ret = Double.parseDouble("0."+String.valueOf(key*a).split("\\.")[1])*m;
		return Long.parseLong(String.valueOf(ret).split("\\.")[0]);//向下取整
	}

}
