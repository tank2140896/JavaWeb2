package com.javaweb.util.help.sort;

//选择（冒泡）排序（相比插入排序，可以理解为【从前往后】）
public class SelectSort<T> implements SortTemplete<T> {

	/**
	<<算法导论>>的伪代码如下:
	for i=1 to A.length-1
		for j=A.length downto i+1
			if A[j]<A[j-1]
				exchange A[j] with A[j-1]
	*/
	public void sort(Comparable<T>[] array) {
		final int arrayLength = array.length;
		for(int i = 0;i<arrayLength;i++){
			int minIndex = i;//使用此变量就不必一比较符合条件就马上交换两个值，现在只需最终全部比完再交换
			for(int j=i+1;j<arrayLength;j++){
				if(more(array[minIndex],array[j])){//从小到大
					minIndex = j;
				}
			}
			exchange(array,i,minIndex);
		}
	}
	
}
