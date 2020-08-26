package com.javaweb.util.help.sort;

//冒泡排序
public class BubbleSort<T> implements SortTemplete<T> {

	/**
	<<算法导论>>的伪代码如下:
	for i=1 to A.length-1
		for j=A.length downto i+1
			if A[j]<A[j-1]
				exchange A[j] with A[j-1]
	*/
	public void sort(Comparable<T>[] array) {
		final int arrayLength = array.length;
		int minIndex = 0;//哨兵
		for(int i=0;i<arrayLength;i++){
			minIndex = i;//使用此变量就不必一比较符合条件就马上交换两个值，现在只需最终全部比完再交换，此技巧只能改变交换次数并不能改变比较次数
			for(int j=i+1;j<arrayLength;j++){
				if(less(array[j],array[minIndex])){//从小到大
					minIndex = j;//用于定位当前最小值所在的位置
				}
			}
			exchange(array,i,minIndex);
		}
	}
	
}
