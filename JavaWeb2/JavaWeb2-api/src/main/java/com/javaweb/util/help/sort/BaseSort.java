package com.javaweb.util.help.sort;

import java.lang.Number;;

//这里的泛型必须是封装类型,如Integer,要是也能用int该多好啊
@FunctionalInterface
public interface BaseSort<T extends Number> {
	
	public T[] sort(T[] array);
	
}
