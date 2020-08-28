package com.javaweb.util.help.sort;

import com.javaweb.enums.SortCompareEnum;

@FunctionalInterface
public interface SortTemplete<T> extends Comparable<T>{
	
	public void sort(Comparable<T>[] array);

	@Override
	public default int compareTo(T o) {
		return 0;
	}
	
	public default SortCompareEnum sortCompare(SortCompareEnum sortCompareEnum){
		return SortCompareEnum.LESS_TO_MORE;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public default boolean less(Comparable one,Comparable another){
		return one.compareTo(another) < 0;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public default boolean more(Comparable one,Comparable another){
		return one.compareTo(another) > 0;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public default boolean equal(Comparable one,Comparable another){
		return one.compareTo(another) == 0;
	}
	
	/**
	两数交换：
	写法一：array[x] = array[x]^array[y];array[y] = array[x]^array[y];array[x] = array[x]^array[y];
	写法二：x = x+y;y = x-y;x = x-y;
	*/
	public default void exchange(Comparable<T>[] array,int one,int another){
		if(one!=another){
			Comparable<T> c = array[one];
			array[one] = array[another];
			array[another] = c;
		}
	}
	
}
