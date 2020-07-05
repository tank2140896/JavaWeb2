package com.javaweb.util.help.sort;

//插入排序（相比冒泡排序，可以理解为【从后往前】）
public class InsertSort<T> implements SortTemplete<T> {
	
	/**
	<<算法导论>>的伪代码如下:
	for j=2 to A.length
  		key = A[j]
  		i = j-1
  		while i>0 and A[i]>key
  			A[i+1] = A[i]
  			i = i-1
  		A[i+1] = key
	*/
	public void sort(Comparable<T>[] array) {
		for (int i = 1; i < array.length; i++) {
			final Comparable<T> key = array[i];//待插入的数
			int compareStartIndex = i-1;//需要比较的数的下标，起始比较永远比待插入的数的下标小1
			while(compareStartIndex>=0&&less(key,array[compareStartIndex])){
				array[compareStartIndex+1] = array[compareStartIndex];//需要比较的数向右移动一位
				compareStartIndex--;//继续往前推一位比较
			}
			array[compareStartIndex+1] = key;//因为之前已经先--往前多推一位了，所以这里要补回来一位
		}
	}
	
}
